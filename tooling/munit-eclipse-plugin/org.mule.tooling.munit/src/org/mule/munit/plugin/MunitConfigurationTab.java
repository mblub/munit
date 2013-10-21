/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.plugin;


import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.jdt.internal.junit.launcher.AssertionVMArg;

public class MunitConfigurationTab extends AbstractLaunchConfigurationTabGroup
{

    /* (non-Javadoc)
     * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog, java.lang.String)
     */
    public void createTabs(ILaunchConfigurationDialog dialog, String mode)
    {
        ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
                new LaunchConfigurationTab(),
                new EnvironmentTab()
        };
        setTabs(tabs);
    }

    /* (non-Javadoc)
     * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void setDefaults(ILaunchConfigurationWorkingCopy config)
    {
        super.setDefaults(config);
        AssertionVMArg.setArgDefault(config);
    }

}
