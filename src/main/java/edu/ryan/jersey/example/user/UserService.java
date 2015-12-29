package edu.ryan.jersey.example.user;

import java.util.List;


/**
 * A service for storing and retrieving {@link User}s.
 */
public interface UserService {

    /**
     * Retrieves a {@link User} by its id.
     *
     * @param id the id of the {@link User}
     * @return the {@link User}
     * @throws RuntimeException if the {@link User} wasn't found
     * @throws NullPointerException if id is null
     */
    User getById(String id);

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
     * @return the newly created {@link User}
     * @throws RuntimeException if the toCreate wasn't valid
     * @throws NullPointerException if toCreate was null
     */
    User create(User toCreate);

    /**
     * Updates an existing {@link User}.
     *
     * @param toUpdate the {@link User} to update
     * @return the updated {@link User}
     * @throws RuntimeException if toUpdate wasn't valid
     * @throws NullPointerException if toUpdate or toUpdate.getId() was null
     */
    User update(User toUpdate);

    /**
     * Deletes a {@link User}.
     *
     * @throws NullPointerException if id was null
     */
    void delete(String id);

}
