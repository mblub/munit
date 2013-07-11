package org.mule.munit.runner.mule.context;

import org.mule.api.MuleContext;
import org.mule.api.config.ConfigurationException;
import org.mule.config.ConfigResource;
import org.mule.config.spring.SpringXmlConfigurationBuilder;

import org.springframework.context.ApplicationContext;

/**
 * <p>
 * Override of the {@link SpringXmlConfigurationBuilder} for Munit. It just overrides the creation of the
 * {@link ApplicationContext} in order to create an {@link MunitApplicationContext}
 * </p>
 *
 * @author Federico, Fernando
 * @since 3.3.2
 */
public class MunitSpringXmlConfigurationBuilder extends SpringXmlConfigurationBuilder
{

    private MockingConfiguration configuration;

    public MunitSpringXmlConfigurationBuilder(String configResources, MockingConfiguration configuration) throws ConfigurationException
    {
        super(configResources);
        this.configuration = configuration;
    }

    @Override
    protected ApplicationContext createApplicationContext(MuleContext muleContext, ConfigResource[] configResources) throws Exception
    {
        return new MunitApplicationContext(muleContext, configResources, configuration);
    }
}
