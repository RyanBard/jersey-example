package edu.ryan.jersey.example.todo;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class DefaultTodoService implements TodoService {

    private static final AtomicInteger ID_SEQUENCE = new AtomicInteger(1);
    private static final HashMap<String, Todo> DATA_STORE = new HashMap<>();

    private final TodoDao dao;

    @Inject
    public DefaultTodoService(
    ) {
        this.dao = new MockTodoDao(
            ID_SEQUENCE,
            DATA_STORE
        );
    }

    @Override
    public Todo getById(
        String id
    ) {
        return dao.getById(id);
    }

    @Override
    public List<Todo> getAll() {
        return dao.getAll();
    }

    @Override
    public Todo create(
        Todo o
    ) {
        String id = dao.create(o);
        // TODO - mutate old object or make new object with ID from DB
        return getById(id);
    }

    @Override
    public Todo update(
        Todo o
    ) {
        dao.update(o);
        return o;
    }

    @Override
    public void delete(
        String id
    ) {
        dao.delete(id);
    }

}
