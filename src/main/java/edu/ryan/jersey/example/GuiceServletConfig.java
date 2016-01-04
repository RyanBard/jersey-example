package edu.ryan.jersey.example;

import javax.servlet.ServletContextEvent;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO.
 */
public class GuiceServletConfig extends GuiceServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuiceServletConfig.class);

    private final MetricRegistry metricRegistry;
    private final Slf4jReporter logging;

    /**
     * The guiceInjector is public exposed so Jersey's Guice-HK2-bridge can use it.
     */
    public static Injector guiceInjector;

    /**
     * TODO.
     */
    public GuiceServletConfig() {
        metricRegistry = new MetricRegistry();
        logging = Slf4jReporter.forRegistry(metricRegistry)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .convertRatesTo(TimeUnit.MILLISECONDS)
                .outputTo(LOGGER)
                .withLoggingLevel(Slf4jReporter.LoggingLevel.DEBUG)
                .build();
    }

    @Override
    protected Injector getInjector() {
        LOGGER.info("get injector called");

        // TODO - need to synchronize?
        if (guiceInjector == null) {
            LOGGER.info("creating injector");
            guiceInjector = Guice.createInjector(
                    new ServletModule() {
                        @Override
                        protected void configureServlets() {
                            serve("/hello2").with(ServletForGuice.class);
                        }
                    },
                    new MainGuiceModule(metricRegistry)
            );
        }
        return guiceInjector;
    }

//    // see: https://hk2.java.net/guice-bridge/
//    private void tieInjectorToLocator(
//            ServiceLocator serviceLocator,
//            Injector guiceInjector
//    ) {
//        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
//        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
//        guiceBridge.bridgeGuiceInjector(guiceInjector);
//    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("contextInitialized called");

        logging.start(5, TimeUnit.MINUTES);

        // TODO - uncomment the getInjector call?
//        Injector guiceInjector = getInjector();

//        tieInjectorToLocator(
//                container.getApplicationHandler().getServiceLocator(),
//                guiceInjector
//        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("contextDestroyed called");
        // report before stopping when shutting down so it'll report the last time window's stats
        logging.report();
        logging.stop();
    }

}
