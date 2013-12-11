/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
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
