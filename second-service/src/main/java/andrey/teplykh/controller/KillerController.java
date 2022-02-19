package andrey.teplykh.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class KillerController {
    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok("Hello world");
    }

    @PostMapping(path = "teams/team/{team-id}/{team-name}/{team-size}/{start-cave-id}", produces = "application/json")
    public ResponseEntity createKillerTeam(@PathVariable(name = "team-id") final int teamId,
                                           @PathVariable(name = "team-name") final String teamName,
                                           @PathVariable(name = "team-size") final int teamSize,
                                           @PathVariable(name = "start-cave-id") final int caveId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/team/{team-id}/move-to-cave/{cave-id}")
    public ResponseEntity sendTeamToDragon(@PathVariable(name = "team-id") final int teamId,
                                           @PathVariable(name = "cave-id") final int caveId) {
        return ResponseEntity.ok().build();
    }
}
