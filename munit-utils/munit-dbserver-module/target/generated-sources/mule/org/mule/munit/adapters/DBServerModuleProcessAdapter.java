
package org.mule.munit.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.munit.DBServerModule;
import org.mule.munit.process.ProcessAdapter;
import org.mule.munit.process.ProcessCallback;
import org.mule.munit.process.ProcessTemplate;
import org.mule.munit.process.ProcessTemplate;


/**
 * A <code>DBServerModuleProcessAdapter</code> is a wrapper around {@link DBServerModule } that enables custom processing strategies.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:19-03:00", comments = "Build 3.4.0.1555.8df15c1")
public class DBServerModuleProcessAdapter
    extends DBServerModuleLifecycleAdapter
    implements ProcessAdapter<DBServerModuleCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, DBServerModuleCapabilitiesAdapter> getProcessTemplate() {
        final DBServerModuleCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,DBServerModuleCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, DBServerModuleCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, DBServerModuleCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
