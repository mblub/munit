
package org.mule.munit.adapters;

import javax.annotation.Generated;
import org.mule.munit.MockModule;
import org.mule.munit.basic.Capabilities;
import org.mule.munit.basic.Capability;


/**
 * A <code>MockModuleCapabilitiesAdapter</code> is a wrapper around {@link MockModule } that implements {@link org.mule.api.Capabilities} interface.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:12-03:00", comments = "Build 3.4.0.1555.8df15c1")
public class MockModuleCapabilitiesAdapter
    extends MockModule
    implements Capabilities
{


    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(Capability capability) {
        if (capability == Capability.LIFECYCLE_CAPABLE) {
            return true;
        }
        return false;
    }

}
