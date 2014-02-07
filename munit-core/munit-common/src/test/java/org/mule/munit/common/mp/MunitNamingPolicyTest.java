/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.mp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.4.0
 */
public class MunitNamingPolicyTest
{

    public static final MunitNamingPolicy MUNIT_NAMING_POLICY = new MunitNamingPolicy();

    @Test
    public void tagMustBeMunit()
    {
        assertEquals("ByMUNIT", MUNIT_NAMING_POLICY.getTag());
    }

    @Test
    public void changePrefixIfNull()
    {
        assertEquals("net.munit.empty.Object$$sourceByMUNIT$$e0047e6e", MUNIT_NAMING_POLICY.getClassName(null,"any.source", null, null));
    }


    @Test
    public void changePrefixIfIsJava()
    {
        assertEquals("$java.como$$sourceByMUNIT$$25582bb6", MUNIT_NAMING_POLICY.getClassName("java.como","any.source", null, null));
    }

    @Test
    public void usePrefixOtherwise()
    {
        assertEquals("org.mule$$sourceByMUNIT$$4ba789eb", MUNIT_NAMING_POLICY.getClassName("org.mule","any.source", null, null));
    }
}
