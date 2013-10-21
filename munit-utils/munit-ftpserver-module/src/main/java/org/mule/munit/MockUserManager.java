/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

import org.apache.ftpserver.ftplet.*;

/**
 * <p>Mock user Manager for testing</p>
 *
 * @author Mulesoft Inc.
 */
public class MockUserManager implements UserManager
{

    @Override
    public User getUserByName(String s) throws FtpException
    {
        return new MockUser();
    }

    @Override
    public String[] getAllUserNames() throws FtpException
    {
        return new String[] {"mock"};
    }

    @Override
    public void delete(String s) throws FtpException
    {

    }

    @Override
    public void save(User user) throws FtpException
    {

    }

    @Override
    public boolean doesExist(String s) throws FtpException
    {
        return true;
    }

    @Override
    public User authenticate(Authentication authentication) throws AuthenticationFailedException
    {
        return new MockUser();
    }

    @Override
    public String getAdminName() throws FtpException
    {
        return "mock";
    }

    @Override
    public boolean isAdmin(String s) throws FtpException
    {
        return true;
    }
}
