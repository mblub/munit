/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;


import org.mule.api.annotations.Configurable;

import java.util.Map;

/**
 * <p>Pojo Simplified Representation of a Mime Message</p>
 *
 * @author Mulesoft Inc.
 */
public class MimeMessage
{


    /**
     * <p>Message content</p>
     */
    @Configurable
    public Object withContent;

    /**
     * <p>Mime message headers</p>
     */
    @Configurable
    public Map<String, String> withHeaders;

    public Object getWithContent()
    {
        return withContent;
    }

    public void setWithContent(Object withContent)
    {
        this.withContent = withContent;
    }

    public Map<String, String> getWithHeaders()
    {
        return withHeaders;
    }

    public void setWithHeaders(Map<String, String> withHeaders)
    {
        this.withHeaders = withHeaders;
    }
}
