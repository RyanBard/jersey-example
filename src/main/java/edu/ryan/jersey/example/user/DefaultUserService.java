package edu.ryan.jersey.example.user;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import java.util.List;

import com.codahale.metrics.annotation.Timed;

import static java.util.Objects.requireNonNull;


/**
 * A default implementation of {@link UserService}.
 */
public class DefaultUserService implements UserService {

    private final UserDao dao;

    /**
     * An injectable constructor.
     *
     * @param dao the dao to store and retrieve {@link User}s
     */
    @Inject
    public DefaultUserService(
            @Nonnull UserDao dao
    ) {
        this.dao = requireNonNull(dao);
    }

    @Override
    @Timed
    public User getById(
            String id
    ) {
        // TODO - validate input
        return dao.getById(id);
    }

    @Override
    @Timed
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    @Timed
    public User create(
            @Nonnull User toCreate
    ) {
        // TODO - validate input
        String id = dao.create(toCreate);
        return getById(id);
    }

    @Override
    @Timed
    public User update(
            @Nonnull User toUpdate
    ) {
        // TODO - validate input
        dao.update(toUpdate);
        return getById(toUpdate.getId());
    }

    @Override
    @Timed
    public void delete(
            String id
    ) {
        // TODO - validate input
        dao.delete(id);
    }

}
