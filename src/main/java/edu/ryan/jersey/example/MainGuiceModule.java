package edu.ryan.jersey.example;

import javax.inject.Singleton;

import edu.ryan.jersey.example.user.UserGuiceModule;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.palominolabs.metrics.guice.MetricsInstrumentationModule;

/**
 *  * A guice module to configure the daos and services for the entire app.
 */
public class MainGuiceModule extends AbstractModule {

    private final MetricRegistry metricRegistry;

    /**
     * Constructs the main guice module.
     *
     * @param metricRegistry the metrics registry to initialize the metrics guice module with
     */
    public MainGuiceModule(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Override
    protected void configure() {
        install(new MetricsInstrumentationModule(metricRegistry));
        install(new UserGuiceModule());
        bind(ServletForGuice.class).in(Singleton.class);
    }

}
