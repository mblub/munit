/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.mp;

import net.sf.cglib.core.DefaultNamingPolicy;
import net.sf.cglib.core.Predicate;

/**
 * <p>
 * CGLib Naming Policy for Munit
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.4.0
 */
public class MunitNamingPolicy extends DefaultNamingPolicy
{

    public String getClassName(String prefix, String source, Object key, Predicate names)
    {
        if (prefix == null)
        {
            prefix = "net.munit.empty.Object";
        }
        else if (prefix.startsWith("java"))
        {
            prefix = "$" + prefix;
        }
        String base =
                prefix + "$$" +
                source.substring(source.lastIndexOf('.') + 1) +
                getTag() + "$$" +
                Integer.toHexString(prefix.hashCode());
        return base;
    }

    @Override
    protected String getTag()
    {
        return "ByMUNIT";
    }
}
