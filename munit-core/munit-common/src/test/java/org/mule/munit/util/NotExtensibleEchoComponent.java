package org.mule.munit.util;

import org.mule.api.MuleEventContext;
import org.mule.component.simple.EchoComponent;

/**
 * Created by damiansima on 11/19/14.
 */
public final class NotExtensibleEchoComponent extends EchoComponent {

    public NotExtensibleEchoComponent(String parameters) {

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
