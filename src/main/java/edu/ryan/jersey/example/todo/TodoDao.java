package edu.ryan.jersey.example.todo;

import javax.annotation.Nonnull;

import java.util.List;


public interface TodoDao {

    /**
     *
     * @param id
     * @return
     */
    Todo getById(@Nonnull String id);

    List<Todo> getAll();

    String create(@Nonnull Todo o);

    void update(@Nonnull Todo o);

    void delete(@Nonnull String id);

}
