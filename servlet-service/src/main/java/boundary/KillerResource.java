package boundary;

import dto.PersonDto;
import expetions.BusinessException;
import service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class KillerResource {

    @Inject
    private PersonService service;

    @POST
    @Path("/create")
    public Response createKiller(final PersonDto dto) throws BusinessException {
        service.save(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response getAllPersons() {
        return Response.ok(service.getAll()).build();
    }
}
