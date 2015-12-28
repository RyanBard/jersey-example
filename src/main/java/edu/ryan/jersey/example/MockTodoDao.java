package edu.ryan.jersey.example;

import javax.inject.Inject;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MockTodoDao implements TodoDao {

    private final AtomicInteger idSequence;
    private final Map<String, Todo> dataStore;

    @Inject
    public MockTodoDao(
        AtomicInteger idSequence,
        Map<String, Todo> dataStore
    ) {
        this.idSequence = idSequence;
        this.dataStore = dataStore;
    }

    @Override
    public Todo getById(
        String id
    ) {
        if (!dataStore.containsKey(id)) {
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
        Todo o
    ) {
        String id = "" + idSequence.incrementAndGet();
        // TODO - set the ID in the object before putting into the map
        o.setId(id);
        dataStore.put(id, o);
        return id;
    }

    @Override
    public Todo update(
        Todo o
    ) {
        dataStore.put(o.getId(), o);
        return o;
    }

    @Override
    public void delete(
        String id
    ) {
        dataStore.remove(id);
    }

}
