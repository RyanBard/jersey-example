package edu.ryan.jersey.example;

import java.util.List;


public interface TodoService {

    Todo getById(String id);

    List<Todo> getAll();

    Todo create(Todo o);

    Todo update(Todo o);

    void delete(String id);

}
