package org.mule.munit.util;

import org.mule.api.MuleEventContext;
import org.mule.component.simple.EchoComponent;

/**
 * Created by damiansima on 11/19/14.
 */
public class EchoComponentWithPrimitiveTypeInConstructor extends EchoComponent {

    public EchoComponentWithPrimitiveTypeInConstructor(int parameter) {

    }

    @Override
    public Object onCall(MuleEventContext context) throws Exception
    {
        super.onCall(context);
        return context.getMessage();
    }

    public String echo(String echo)
    {
        return echo;
    }
}
