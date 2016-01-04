package edu.ryan.jersey.example;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO.
 * https://github.com/piersy/jersey2-guice-example-with-test/
 */
public class JerseyApplicationConfig extends ResourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyApplicationConfig.class);

    /**
     * TODO.
     *
     * @param serviceLocator TODO
     */
    @Inject
    public JerseyApplicationConfig(ServiceLocator serviceLocator) {
        LOGGER.info("configuring Jersey");

        packages("edu.ryan.jersey.example");

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(GuiceServletConfig.guiceInjector);
    }

}
