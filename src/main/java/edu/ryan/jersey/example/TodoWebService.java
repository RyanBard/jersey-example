package edu.ryan.jersey.example;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import java.util.List;


@Path("api/todo")
@Produces(MediaType.APPLICATION_JSON)
public class TodoWebService {

    private final TodoService service;

    @Inject
    public TodoWebService(
//        Todo service
    ) {
        this.service = new DefaultTodoService();
    }

    @GET
    @Path("{id}")
    public Todo getById(
        @PathParam("id") String id
    ) {
        return service.getById(id);
    }

    @GET
    public List<Todo> getAll() {
        return service.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Todo create(
        Todo o
    ) {
        return service.create(o);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Todo update(
        @PathParam("id") String id,
        Todo o
    ) {
        // TODO - validate id & o.getId() match
        return service.update(o);
    }

    @DELETE
    @Path("{id}")
    public void delete(
        @PathParam("id") String id
    ) {
        service.delete(id);
    }

}
