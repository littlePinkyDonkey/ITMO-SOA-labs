package boundary;

import expetions.BusinessException;
import expetions.UserDataException;
import service.DragonService;
import util.OperandInfo;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dragons")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class DragonResource {

    @Inject
    private DragonService dragonService;

    @GET
    public Response getAllWithParams(@QueryParam("order_by") final OperandInfo orderBy,
                                     @QueryParam("size") @DefaultValue(value = "5") final Integer pageSize,
                                     @QueryParam("page") @DefaultValue(value = "0") final Integer pageNumber,
                                     @QueryParam("filter_by") final OperandInfo[] filters) throws BusinessException {
        return Response.ok(dragonService.getAll(orderBy, filters, pageNumber, pageSize)).build();
    }

    @GET
    @Path("/{dragon_id}")
    public Response getById(@PathParam("dragon_id") final Long id) throws BusinessException {
        return Response.ok(dragonService.getDragonById(id)).build();
    }

    @GET
    @Path("/extra/sum")
    public Response findSum() throws BusinessException {
        return Response.ok(dragonService.findSum()).build();
    }

    @GET
    @Path("/extra/min")
    public Response findWithMinId() throws BusinessException {
        return Response.ok(dragonService.getWithMinId()).build();
    }

    @DELETE
    @Path("/{dragon_id}")
    public Response deleteById(@PathParam("dragon_id") final Long id) throws BusinessException, UserDataException {
        dragonService.removeElement(id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/extra/{character}")
    public Response deleteByCharacter(@PathParam("character") final String character) throws BusinessException {
        final int updated = dragonService.removeElementByCharacter(character);
        if (updated > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/add")
    public Response createDragon(final String request) throws BusinessException {
        final String response = dragonService.save(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/update")
    public Response updateDragon(final String request) throws BusinessException {
        final String response = dragonService.updateElement(request);
        return Response.ok(response).build();
    }
}
