package andrei.teplykh.handler;

import andrei.teplykh.expetions.BusinessException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionHandler implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(final BusinessException exception) {
        return Response.status(exception.getStatus()).entity(exception.getMessage()).build();
    }
}
