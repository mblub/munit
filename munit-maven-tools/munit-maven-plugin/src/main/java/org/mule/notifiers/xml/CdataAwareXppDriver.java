/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.notifiers.xml;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.translate.NumericEntityEscaper;

import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class CdataAwareXppDriver extends XppDriver {

    /**
     * List of field names that will be wrapped as CDATA
     */
    private static final List<String> CDATA_FIELDS = new ArrayList<String>();;

    static {
        /**
         * Wrap "failure" field to encapsulate StackTrace
         */
        CDATA_FIELDS.add("failure");
    }

    public CdataAwareXppDriver() {
        super();
    }

    /**
     * Override createWriter to wrap fields listed in CDATA_FIELDS with CDATA block
     */
    @Override
    public HierarchicalStreamWriter createWriter(Writer out) {
        return new PrettyPrintWriter(out) {
            boolean cdata = false;

            public void startNode(String name) {
                super.startNode(name);
                cdata = CDATA_FIELDS.contains(name);
            }
            
            protected void writeText(QuickWriter writer, String text) {
                if (cdata) {
                    writer.write("<![CDATA[");
                    writer.write(NumericEntityEscaper.below(0x20).translate(text));
                    writer.write("]]>");
                } else {
                    super.writeText(writer, text);
                }
            }
        };
    }
}
