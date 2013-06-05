
package org.mule.munit.adapters;

import javax.annotation.Generated;
import org.mule.munit.DBServerModule;
import org.mule.munit.basic.MetadataAware;


/**
 * A <code>DBServerModuleMetadataAdapater</code> is a wrapper around {@link DBServerModule } that adds support for querying metadata about the extension.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:19-03:00", comments = "Build 3.4.0.1555.8df15c1")
public class DBServerModuleMetadataAdapater
    extends DBServerModuleCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "dbserver";
    private final static String MODULE_VERSION = "3.4-M4-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.4.0";
    private final static String DEVKIT_BUILD = "3.4.0.1555.8df15c1";

    public String getModuleName() {
        return MODULE_NAME;
    }

    public String getModuleVersion() {
        return MODULE_VERSION;
    }

    public String getDevkitVersion() {
        return DEVKIT_VERSION;
    }

    public String getDevkitBuild() {
        return DEVKIT_BUILD;
    }

}
