package andrei.teplykh.boundary;

import andrei.teplykh.config.ContextProvider;
import andrei.teplykh.dto.DragonDto;
import andrei.teplykh.util.GsonQualifier;
import com.google.gson.Gson;
import andrei.teplykh.expetions.BusinessException;
import andrei.teplykh.expetions.UserDataException;
import andrei.teplykh.service.DragonService;
import andrei.teplykh.util.OperandInfo;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dragons")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class DragonResource {

    private DragonService dragonServiceImpl;

    @Inject
    @GsonQualifier
    private Gson gson;

    public DragonResource() throws NamingException {
        Context context = new ContextProvider().getContext();
        Object ref = context.lookup("ejb:/ejb-simple-jar-with-dependencies/DragonServiceImpl!andrei.teplykh.service.DragonService");
        this.dragonServiceImpl = (DragonService) PortableRemoteObject.narrow(ref, DragonService.class);
    }

    @GET
    public Response getAllWithParams(@QueryParam("order_by") final OperandInfo orderBy,
                                     @QueryParam("size") @DefaultValue(value = "5") final Integer pageSize,
                                     @QueryParam("page") @DefaultValue(value = "0") final Integer pageNumber,
                                     @QueryParam("filter_by") final OperandInfo[] filters) throws BusinessException {
        return Response.ok(gson.toJson(dragonServiceImpl.getAll(orderBy, filters, pageNumber, pageSize))).build();
    }

    @GET
    @Path("/{dragon_id}")
    public Response getById(@PathParam("dragon_id") final Long id) throws BusinessException {
        return Response.ok(gson.toJson(dragonServiceImpl.getDragonById(id))).build();
    }

    @GET
    @Path("/extra/sum")
    public Response findSum() throws BusinessException {
        return Response.ok(dragonServiceImpl.findSum()).build();
    }

    @GET
    @Path("/extra/min")
    public Response findWithMinId() throws BusinessException {
        return Response.ok(gson.toJson(dragonServiceImpl.getWithMinId())).build();
    }

    @DELETE
    @Path("/{dragon_id}")
    public Response deleteById(@PathParam("dragon_id") final Long id) throws BusinessException, UserDataException {
        dragonServiceImpl.removeElement(id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/extra/{character}")
    public Response deleteByCharacter(@PathParam("character") final String character) throws BusinessException {
        final int updated = dragonServiceImpl.removeElementByCharacter(character);
        if (updated > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/add")
    public Response createDragon(final String request) throws BusinessException {
        final DragonDto response = dragonServiceImpl.save(gson.fromJson(request, DragonDto.class));
        return Response.status(Response.Status.CREATED).entity(gson.toJson(response)).build();
    }

    @PUT
    @Path("/update")
    public Response updateDragon(final String request) throws BusinessException {
        final DragonDto response = dragonServiceImpl.updateElement(gson.fromJson(request, DragonDto.class));
        return Response.ok(gson.toJson(response)).build();
    }
}
