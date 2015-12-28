package edu.ryan.jersey.example;

import java.util.List;


public interface TodoDao {

    Todo getById(String id);

    List<Todo> getAll();

    String create(Todo o);

    Todo update(Todo o);

    void delete(String id);

}
