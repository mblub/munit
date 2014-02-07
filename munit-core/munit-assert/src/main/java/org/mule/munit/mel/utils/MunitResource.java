/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.mel.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * <p>
 * This is the class returned by the GetResourceFunction. It has a path that
 * points to the resource wanted, and it has a couple of methods that can
 * convert its content into String, InputStream or byte array
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitResource
{

    private String path;
    static Logger logger = Logger.getLogger(MunitResource.class);

    public MunitResource(String path)
    {
        this.path = path;
    }

    public InputStream asStream()
    {
        InputStream streamImput = getClass().getClassLoader().getResourceAsStream(path);
        if (streamImput == null)
        {
            throw new IllegalArgumentException(
                    "The path provided to get the resource does not exist");
        }

        return streamImput;
    }

    public String asString()
    {
        try
        {
            return IOUtils.toString(this.asStream());
        }
        catch (IOException ioe)
        {
            logger.error("The file is corrupted");
            return null;
        }
    }

    public byte[] asByteArray()
    {
        byte[] byteArrayImput = null;
        try
        {
            byteArrayImput = IOUtils.toByteArray(asStream());
        }
        catch (IOException ioe)
        {
            logger.error("The file is corrupted");
        }
        return byteArrayImput;
    }

}
