package edu.ryan.jersey.example.user;

import javax.annotation.Nonnull;

import java.util.List;


/**
 * A dao for storing and retrieving {@link User}s.
 */
public interface UserDao {

    /**
     * Retrieves a {@link User} by its id.
     *
     * @param id the id of the {@link User}
     * @return the {@link User}
     * @throws RuntimeException if the {@link User} wasn't found
     * @throws NullPointerException if id is null
     */
    User getById(@Nonnull String id);

    /**
     * Retrieves all {@link User}s.
     *
     * @return the {@link User}s.
     */
    List<User> getAll();

    /**
     * Creates a new {@link User}.
     *
     * @param toCreate the {@link User} to create
     * @return the id of the newly created {@link User}
     * @throws NullPointerException if toCreate was null
     */
    String create(@Nonnull User toCreate);

    /**
     * Updates an existing {@link User}.
     *
     * @param toUpdate the {@link User} to update
     * @throws NullPointerException if toUpdate or toUpdate.getId() was null
     */
    void update(@Nonnull User toUpdate);

    /**
     * Deletes a {@link User}.
     *
     * @throws NullPointerException if id was null
     */
    void delete(@Nonnull String id);

}
