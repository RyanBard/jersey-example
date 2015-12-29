package edu.ryan.jersey.example.user;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static java.util.Objects.requireNonNull;


@Path("api/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserWebService {

    private final UserService service;

    /**
     * An injectable constructor.
     *
     * @param service a service to store and retrieve {@link User}s
     */
    @Inject
    public UserWebService(
            @Nonnull UserService service
    ) {
        this.service = requireNonNull(service);
    }

    @GET
    @Path("{id}")
    public User getById(
            @PathParam("id") String id
    ) {
        return service.getById(id);
    }

    @GET
    public List<User> getAll() {
        return service.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public User create(
            User toCreate
    ) {
        return service.create(toCreate);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public User update(
            @PathParam("id") String id,
            User toUpdate
    ) {
        // TODO - validate id & o.getId() match
        return service.update(toUpdate);
    }

    @DELETE
    @Path("{id}")
    public void delete(
            @PathParam("id") String id
    ) {
        service.delete(id);
    }

}
