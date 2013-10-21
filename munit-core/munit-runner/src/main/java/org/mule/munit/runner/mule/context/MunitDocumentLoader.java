package org.mule.munit.runner.mule.context;

import org.apache.xerces.parsers.DOMParser;
import org.springframework.beans.factory.xml.DefaultDocumentLoader;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;

/**
 * <p>
 * We change the document loader in order to get the line numbers as element attribute.
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.4
 */
public class MunitDocumentLoader extends DefaultDocumentLoader
{

    static final String JAXP_SCHEMA_LANGUAGE =
            "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA =
            "http://www.w3.org/2001/XMLSchema";

    public Document loadDocument(InputSource inputSource, EntityResolver entityResolver,
                                 ErrorHandler errorHandler, int validationMode, boolean namespaceAware) throws Exception
    {

        DOMParser xmlReader = new MunitDomParser();
        xmlReader.setFeature("http://xml.org/sax/features/validation", Boolean.TRUE);
        xmlReader.setFeature("http://xml.org/sax/features/namespaces", Boolean.TRUE);
        xmlReader.setFeature("http://apache.org/xml/features/validation/schema", Boolean.TRUE);
        xmlReader.setFeature("http://apache.org/xml/features/xinclude", Boolean.FALSE);
        xmlReader.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

        xmlReader.setEntityResolver(entityResolver);
        xmlReader.setErrorHandler(errorHandler);

        xmlReader.parse(inputSource);

        return xmlReader.getDocument();
    }

}





