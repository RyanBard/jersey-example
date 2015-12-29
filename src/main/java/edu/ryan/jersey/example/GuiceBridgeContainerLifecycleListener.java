package edu.ryan.jersey.example;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A jersey {@link ContainerLifecycleListener} so I can register the Hk2GuiceBridge.
 */
public class GuiceBridgeContainerLifecycleListener implements ContainerLifecycleListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuiceBridgeContainerLifecycleListener.class);

    private final MetricRegistry metricRegistry;
    private final Slf4jReporter logging;

    public GuiceBridgeContainerLifecycleListener() {
        metricRegistry = new MetricRegistry();
        logging = Slf4jReporter.forRegistry(metricRegistry)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .convertRatesTo(TimeUnit.MILLISECONDS)
                .outputTo(LOGGER)
                .withLoggingLevel(Slf4jReporter.LoggingLevel.DEBUG)
                .build();
    }

    @Override
    public void onStartup(Container container) {
        logging.start(5, TimeUnit.MINUTES);

        Injector guiceInjector = createGuiceInjector();
        tieInjectorToLocator(
                container.getApplicationHandler().getServiceLocator(),
                guiceInjector
        );
    }

    // see: https://github.com/google/guice/wiki/GettingStarted
    private Injector createGuiceInjector() {
        return Guice.createInjector(new MainGuiceModule(metricRegistry));
    }
    // see: https://hk2.java.net/guice-bridge/
    private void tieInjectorToLocator(
            ServiceLocator serviceLocator,
            Injector guiceInjector
    ) {
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(guiceInjector);
    }

    @Override
    public void onReload(Container container) {
        // empty
    }

    @Override
    public void onShutdown(Container container) {
         // report before stopping when shutting down so it'll report the last time window's stats
         logging.report();
         logging.stop();
    }

}
