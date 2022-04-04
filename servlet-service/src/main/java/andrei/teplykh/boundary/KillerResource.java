package andrei.teplykh.boundary;

import andrei.teplykh.config.ContextProvider;
import andrei.teplykh.dto.PersonDto;
import andrei.teplykh.expetions.BusinessException;
import andrei.teplykh.service.DragonService;
import andrei.teplykh.service.PersonService;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class KillerResource {

    private PersonService service;

    public KillerResource() throws NamingException {
        Context context = new ContextProvider().getContext();
        Object ref = context.lookup("pool:/ejb/DragonServiceImpl!andrei.teplykh.service.DragonService");
        this.service = (PersonService) PortableRemoteObject.narrow(ref, PersonService.class);
    }

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
