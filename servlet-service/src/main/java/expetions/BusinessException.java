package expetions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.ws.rs.core.Response;

@Getter
@AllArgsConstructor
public class BusinessException extends Exception {
    private Response.Status status;
    private String message;
}
