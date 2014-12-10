package org.mule.munit;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by damiansima on 12/10/14.
 */
public class AttributeTest {
    public static final String NAME = "attribute_name";
    public static final String WHERE_VALUE = "where_value";

    public Attribute attr;

    @Before
    public void setUp() {
        attr = Attribute.create(NAME, WHERE_VALUE);
    }

    @Test
    public void testCreate() {
        Assert.assertNotNull("The attribute should not be null", attr);
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("The name of the attribute is wrong", NAME, attr.getName());
    }

    @Test
    public void testGetwhereValue() {
        Assert.assertEquals("The name of the whereValue is wrong", WHERE_VALUE, attr.getWhereValue());
    }
}
