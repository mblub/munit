/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.mule.munit.runner.mule.MunitSuiteRunner;
import org.mule.munit.runner.mule.result.MunitResult;
import org.mule.munit.runner.mule.result.SuiteResult;
import org.mule.munit.runner.mule.result.notification.DummyNotificationListener;
import org.mule.munit.runner.mule.result.notification.NotificationListener;
import org.mule.munit.runner.output.DefaultOutputHandler;
import org.mule.notifiers.NotificationListenerDecorator;
import org.mule.notifiers.StreamNotificationListener;
import org.mule.notifiers.xml.XmlNotificationListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * Runs tests
 *
 * @goal test
 * @requiresDependencyResolution test
 * @goal test
 * @phase test
 */

public class MUnitMojo
        extends AbstractMojo
{

    public static final String TARGET_SUREFIRE_REPORTS_MUNIT_TXT = "/target/surefire-reports/munit.";
    public static final String TARGET_SUREFIRE_REPORTS_TEST_MUNIT_XML = "/target/surefire-reports/TEST-munit.";
    /**
     * @parameter expression="${project}"
     * @required
     */
    protected MavenProject project;

    /**
     * @parameter expression="${munit.test}"
     */
    protected String munittest;

    /**
     * @parameter expression="${log.to.file}" default-value="false"
     */
    protected boolean logToFile;

    /**
     * The classpath elements of the project being tested.
     *
     * @parameter expression="${project.testClasspathElements}"
     * @required
     * @readonly
     */
    protected List<String> classpathElements;


    public void execute()
            throws MojoExecutionException
    {

        if (!"true".equals(System.getProperty("skipTests")))
        {
            if (logToFile)
            {
                System.setProperty(DefaultOutputHandler.OUTPUT_FOLDER_PROPERTY, project.getBasedir() + TARGET_SUREFIRE_REPORTS_MUNIT_TXT + "%s-output.txt");
            }

            List testResources = project.getTestResources();
            for (Object o : testResources)
            {
                Resource testResource = (Resource) o;
                testResource.getTargetPath();
            }

            try
            {
                List<SuiteResult> results = new ArrayList<SuiteResult>();
                addUrlsToClassPath(makeClassPath());
                File testFolder = new File(project.getBasedir(), "src/test/munit");
                if (testFolder == null || !testFolder.exists())
                {
                    return;
                }
                Collection<File> allFiles = FileUtils.listFiles(testFolder, null, true);
                for (File file : allFiles)
                {

                    String fileName = file.getPath().replace(testFolder.getPath() + File.separator, "");
                    if (fileName.endsWith(".xml") && validateFilter(fileName))
                    {
                        results.add(buildRunnerFor(fileName).run());
                    }

                }

                show(results);

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                e.printStackTrace();
            }
            catch (NoSuchMethodException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            finally
            {
            }
        }

    }

    private void show(List<SuiteResult> results) throws MojoExecutionException
    {
        boolean success = true;

        System.out.println();
        System.out.println("\t=====================================");
        System.out.println("\t  Munit Summary                      ");
        System.out.println("\t=====================================");

        for (SuiteResult run : results)
        {
            List<MunitResult> failingTests = run.getFailingTests();
            List<MunitResult> errorTests = run.getErrorTests();
            System.out.println("\t >> " + FilenameUtils.getName(run.getTestName()) + " test result: Errors: " + errorTests.size() + ", Failures:" + failingTests.size() + ", Skipped: " + run.getNumberOfSkipped());

            showFailures(failingTests);
            showError(errorTests);

            if (!failingTests.isEmpty() || !errorTests.isEmpty())
            {
                success = false;
            }
        }

        if (!success)
        {
            throw new MojoExecutionException("MUnit Tests Failed!!!");
        }
    }

    private void showFailures(List<MunitResult> failingTests)
    {
        showUnsuccessfulTests(failingTests, "FAILED");
    }

    private void showError(List<MunitResult> errorTests)
    {
        showUnsuccessfulTests(errorTests, "ERROR");
    }

    private void showUnsuccessfulTests(List<MunitResult> unsuccessfulTests, String unsuccessfulTag) {
        if (!unsuccessfulTests.isEmpty())
        {
            for (MunitResult result : unsuccessfulTests)
            {
                System.out.println("\t\t --- " + result.getTestName() + " <<< " + unsuccessfulTag);
            }
        }
    }

    private MunitSuiteRunner buildRunnerFor(String fileName)
    {
        MunitSuiteRunner runner = new MunitSuiteRunner(fileName);
        NotificationListenerDecorator listener = new NotificationListenerDecorator();
        listener.addNotificationListener(new StreamNotificationListener(System.out));
        listener.addNotificationListener(buildFileNotificationListener(fileName));
        listener.addNotificationListener(buildXmlNotificationListener(fileName));
        runner.setNotificationListener(listener);
        return runner;
    }

    private NotificationListener buildFileNotificationListener(String fileName)
    {
        String name = fileName.replace(".xml", ".txt");
        fileName = fileName.replace('/', '.');
        try
        {
            return new StreamNotificationListener(new PrintStream(new FileOutputStream(new File(project.getBasedir() + TARGET_SUREFIRE_REPORTS_MUNIT_TXT + name))));
        }
        catch (FileNotFoundException e)
        {
            return new DummyNotificationListener();
        }
    }

    private NotificationListener buildXmlNotificationListener(String fileName)
    {
    	fileName = fileName.replace('/', '.');
        try
        {
            return new XmlNotificationListener(fileName, new PrintStream(new FileOutputStream(new File(project.getBasedir() + TARGET_SUREFIRE_REPORTS_TEST_MUNIT_XML + fileName))));
        }
        catch (FileNotFoundException e)
        {
            return new DummyNotificationListener();
        }
    }

    private boolean validateFilter(String fileName)
    {
        if (munittest == null)
        {
            return true;
        }

        return fileName.matches(munittest);
    }

    public URLClassLoader getClassPath(List<URL> classpath)
    {
        return new URLClassLoader(classpath.toArray(new URL[classpath.size()]), getClass().getClassLoader());
    }

    /**
     * Creates a classloader for loading tests.
     * <p/>
     * <p/>
     * We need to be able to see the same JUnit classes between this code and the mtest code,
     * but everything else should be isolated.
     */
    private List<URL> makeClassPath() throws MalformedURLException
    {

        List<URL> urls = new ArrayList<URL>(classpathElements.size());

        for (String e : classpathElements)
        {
            urls.add(new File(e).toURL());
        }
        return urls;
    }

    private void addUrlsToClassPath(List<URL> urls) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        ClassLoader sysCl = Thread.currentThread().getContextClassLoader();
        Class refClass = URLClassLoader.class;
        Method methodAddUrl = refClass.getDeclaredMethod("addURL", new Class[] {URL.class});
        methodAddUrl.setAccessible(true);
        for (Iterator it = urls.iterator(); it.hasNext(); )
        {
            URL url = (URL) it.next();
            methodAddUrl.invoke(sysCl, url);
        }
    }
}
