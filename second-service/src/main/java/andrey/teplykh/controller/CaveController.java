package andrey.teplykh.controller;

import andrey.teplykh.service.CaveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cave")
@AllArgsConstructor
public class CaveController {

    private final CaveService caveService;

    @GetMapping(produces = "application/json")
    public ResponseEntity getAllCaves() {
        return ResponseEntity.ok(caveService.getAllCaves());
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity createCave() {
        return ResponseEntity.ok(caveService.createCave());
    }
}
