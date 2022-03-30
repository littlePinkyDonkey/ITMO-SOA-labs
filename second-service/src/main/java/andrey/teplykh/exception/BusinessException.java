package andrey.teplykh.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BusinessException extends Exception {
    private HttpStatus code;
    private String message;
}
