
package org.mule.munit.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.munit.MockModule;
import org.mule.munit.process.ProcessAdapter;
import org.mule.munit.process.ProcessCallback;
import org.mule.munit.process.ProcessTemplate;
import org.mule.munit.process.ProcessTemplate;


/**
 * A <code>MockModuleProcessAdapter</code> is a wrapper around {@link MockModule } that enables custom processing strategies.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:12-03:00", comments = "Build 3.4.0.1555.8df15c1")
public class MockModuleProcessAdapter
    extends MockModuleLifecycleAdapter
    implements ProcessAdapter<MockModuleCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, MockModuleCapabilitiesAdapter> getProcessTemplate() {
        final MockModuleCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,MockModuleCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, MockModuleCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, MockModuleCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
