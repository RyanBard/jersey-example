package edu.ryan.jersey.example.user;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;


/**
 * A mock implementation of {@link UserDao} that uses a {@link Map} for its datastore.
 */
public class MockUserDao implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockUserDao.class);

    private final AtomicInteger idSequence;
    private final Map<String, User> dataStore;

    /**
     * An injectable constructor.
     *
     * @param idSequence an {@link AtomicInteger} for id sequences
     * @param dataStore a {@link Map} for storing and retrieving {@link User}s
     */
    @Inject
    public MockUserDao(
            @Nonnull AtomicInteger idSequence,
            @Nonnull Map<String, User> dataStore
    ) {
        this.idSequence = requireNonNull(idSequence);
        this.dataStore = requireNonNull(dataStore);
    }

    @Override
    @Timed
    public User getById(
            @Nonnull String id
    ) {
        LOGGER.debug("getById called, id={}", id);
        if (!dataStore.containsKey(requireNonNull(id))) {
            throw new RuntimeException("not found: " + id);
        }
        return dataStore.get(id);
    }

    @Override
    @Timed
    public List<User> getAll() {
        LOGGER.debug("getAll called");
        return new ArrayList<>(dataStore.values());
    }

    @Override
    @Timed
    public String create(
            @Nonnull User toCreate
    ) {
        LOGGER.debug("create called, toCreate={}", toCreate);
        requireNonNull(toCreate);
        String id = "" + idSequence.incrementAndGet();
        toCreate.setId(id);
        dataStore.put(id, toCreate);
        return id;
    }

    @Override
    @Timed
    public void update(
            @Nonnull User toUpdate
    ) {
        LOGGER.debug("update called, toUpdate={}", toUpdate);
        dataStore.put(toUpdate.getId(), toUpdate);
    }

    @Override
    @Timed
    public void delete(
            @Nonnull String id
    ) {
        LOGGER.debug("delete called, id={}", id);
        dataStore.remove(requireNonNull(id));
    }

}
