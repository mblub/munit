package org.mule.munit.runner.mule.context;

import org.mule.config.spring.MuleBeanDefinitionDocumentReader;

import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.w3c.dom.Element;

/**
 * <p>
 * The {@link MunitBeanDefinitionDocumentReader} that overrides {@link MuleBeanDefinitionDocumentReader} to create
 * a {@link MunitBeanDefinitionParserDelegate}
 * </p>
 *
 * @author Federico, Fernando
 * @since 3.3.2
 */
public class MunitBeanDefinitionDocumentReader extends MuleBeanDefinitionDocumentReader
{
    @Override
    protected BeanDefinitionParserDelegate createHelper(XmlReaderContext readerContext, Element root, BeanDefinitionParserDelegate parentDelegate)
    {
        BeanDefinitionParserDelegate delegate = new MunitBeanDefinitionParserDelegate(readerContext, this);
        delegate.initDefaults(root, parentDelegate);
        return delegate;
    }
}
