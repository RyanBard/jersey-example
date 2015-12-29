package edu.ryan.jersey.example.user;

import javax.inject.Singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;


/**
 * A guice module to configure the daos and services for {@link User}s.
 */
public class UserGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserDao.class).to(MockUserDao.class);
        bind(UserService.class).to(DefaultUserService.class);
    }

    @Provides
    @Singleton
    private AtomicInteger provideIdSequence() {
        return new AtomicInteger(0);
    }

    @Provides
    @Singleton
    private Map<String, User> provideDataStore() {
        return new ConcurrentHashMap<>();
    }

}
