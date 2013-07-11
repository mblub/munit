package org.mule.munit.runner.mule.context;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.w3c.dom.Element;

/**
 * @author Federico, Fernando
 * @since 3.3.2
 */
public class MunitBeanDefinitionDocumentReaderTest
{

    private XmlReaderContext readerContext = mock(XmlReaderContext.class);
    private Element element = mock(Element.class);
    private BeanDefinitionParserDelegate parentDelegate = mock(BeanDefinitionParserDelegate.class);

    @Test
    public void testDelegateInstanceCreation()
    {
        assertTrue(new MunitBeanDefinitionDocumentReader().createHelper(readerContext, element, parentDelegate) instanceof MunitBeanDefinitionParserDelegate);
    }
}
