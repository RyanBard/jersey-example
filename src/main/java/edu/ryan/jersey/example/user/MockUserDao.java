package edu.ryan.jersey.example.user;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.requireNonNull;


/**
 * A mock implementation of {@link UserDao} that uses a {@link Map} for its datastore.
 */
public class MockUserDao implements UserDao {

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
    public User getById(
            @Nonnull String id
    ) {
        if (!dataStore.containsKey(requireNonNull(id))) {
            throw new RuntimeException("not found: " + id);
        }
        return dataStore.get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(dataStore.values());
    }

    @Override
    public String create(
            @Nonnull User toCreate
    ) {
        requireNonNull(toCreate);
        String id = "" + idSequence.incrementAndGet();
        toCreate.setId(id);
        dataStore.put(id, toCreate);
        return id;
    }

    @Override
    public void update(
            @Nonnull User toUpdate
    ) {
        dataStore.put(toUpdate.getId(), toUpdate);
    }

    @Override
    public void delete(
            @Nonnull String id
    ) {
        dataStore.remove(requireNonNull(id));
    }

}
