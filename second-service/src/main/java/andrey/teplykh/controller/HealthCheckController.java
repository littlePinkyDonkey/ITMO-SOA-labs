package andrey.teplykh.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/health")
@AllArgsConstructor
public class HealthCheckController {

    @GetMapping
    public ResponseEntity check() {
        return ResponseEntity.ok("test 8080");
    }
}
