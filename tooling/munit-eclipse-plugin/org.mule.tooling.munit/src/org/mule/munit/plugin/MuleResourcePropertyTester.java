/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.plugin;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;

/**
 * Property tester for tests on Mule resources.
 *
 * @author Derek
 */
public class MuleResourcePropertyTester extends PropertyTester
{

    /**
     * Property indicating if a resource is in a Mule project
     */
    public static final String PROP_IS_IN_MULE_PROJECT = "isInMuleProject";

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[],
     * java.lang.Object)
     */
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue)
    {
        if (receiver instanceof IResource)
        {
            if (PROP_IS_IN_MULE_PROJECT.equals(property))
            {
                final IResource resource = ((IResource) receiver);
                if (resource.getFileExtension().equals("xml"))
                {
                    return true;
                }
            }
        }
        return false;
    }
}