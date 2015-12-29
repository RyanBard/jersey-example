package edu.ryan.jersey.example;

import java.util.concurrent.TimeUnit;

import edu.ryan.jersey.example.user.UserGuiceModule;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.google.inject.AbstractModule;
import com.palominolabs.metrics.guice.MetricsInstrumentationModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  * A guice module to configure the daos and services for the entire app.
 */
public class MainGuiceModule extends AbstractModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainGuiceModule.class);
    @Override
    protected void configure() {
        MetricRegistry metricRegistry = new MetricRegistry();
        Slf4jReporter logging = Slf4jReporter.forRegistry(metricRegistry)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .convertRatesTo(TimeUnit.MILLISECONDS)
                .outputTo(LOGGER)
                .withLoggingLevel(Slf4jReporter.LoggingLevel.DEBUG)
                .build();
        logging.start(5, TimeUnit.MINUTES);
        // logging.report();
        // logging.stop(); // report before stopping when shutting down so it'll report the last window's stats
        // TODO - use the contextInitialized & contextDestroyed hooks to do this properly?
        install(new MetricsInstrumentationModule(metricRegistry));
        install(new UserGuiceModule());
    }

}
