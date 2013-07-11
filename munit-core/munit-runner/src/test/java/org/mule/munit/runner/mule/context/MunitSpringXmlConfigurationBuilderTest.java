package org.mule.munit.runner.mule.context;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.mule.api.MuleContext;
import org.mule.config.ConfigResource;

import org.junit.Test;

/**
 * @author Federico, Fernando
 * @since 3.4
 */
public class MunitSpringXmlConfigurationBuilderTest
{

    private MockingConfiguration configuration = mock(MockingConfiguration.class);
    private MuleContext muleContext = mock(MuleContext.class);

    @Test
    public void testInstanceCreation() throws Exception
    {
        MunitSpringXmlConfigurationBuilder builder = new MunitSpringXmlConfigurationBuilder("munit-config.xml", configuration);
        assertTrue(builder.createApplicationContext(muleContext, new ConfigResource[]{}) instanceof MunitApplicationContext);
    }
}
