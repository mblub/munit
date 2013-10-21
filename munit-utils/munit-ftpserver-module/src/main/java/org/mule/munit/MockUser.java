/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.usermanager.impl.TransferRateRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Mock User for testing</p>
 *
 * @author Mulesoft Inc.
 */
public class MockUser implements User
{

    @Override
    public String getName()
    {
        return "mock";
    }

    @Override
    public String getPassword()
    {
        return "mock";
    }

    @Override
    public List<Authority> getAuthorities()
    {
        return new ArrayList<Authority>();
    }

    @Override
    public List<Authority> getAuthorities(Class<? extends Authority> aClass)
    {
        return new ArrayList<Authority>();
    }

    @Override
    public AuthorizationRequest authorize(AuthorizationRequest authorizationRequest)
    {
        return new TransferRateRequest()
        {
        };
    }

    @Override
    public int getMaxIdleTime()
    {
        return 5;
    }

    @Override
    public boolean getEnabled()
    {
        return true;
    }

    @Override
    public String getHomeDirectory()
    {
        return "";
    }
}
