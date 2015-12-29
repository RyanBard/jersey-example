package edu.ryan.jersey.example.todo;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.requireNonNull;


public class MockTodoDao implements TodoDao {

    private final AtomicInteger idSequence;
    private final Map<String, Todo> dataStore;

    @Inject
    public MockTodoDao(
            @Nonnull AtomicInteger idSequence,
            @Nonnull Map<String, Todo> dataStore
    ) {
        this.idSequence = requireNonNull(idSequence);
        this.dataStore = requireNonNull(dataStore);
    }

    @Override
    public Todo getById(
            @Nonnull String id
    ) {
        if (!dataStore.containsKey(requireNonNull(id))) {
            throw new RuntimeException("not found: " + id);
        }
        return dataStore.get(id);
    }

    @Override
    public List<Todo> getAll() {
        return new ArrayList<>(dataStore.values());
    }

    @Override
    public String create(
            @Nonnull Todo o
    ) {
        requireNonNull(o);
        String id = "" + idSequence.incrementAndGet();
        // TODO - set the ID in the object before putting into the map
        o.setId(id);
        dataStore.put(id, o);
        return id;
    }

    @Override
    public void update(
            @Nonnull Todo o
    ) {
        dataStore.put(o.getId(), o);
    }

    @Override
    public void delete(
            @Nonnull String id
    ) {
        dataStore.remove(requireNonNull(id));
    }

}
