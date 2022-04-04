package andrey.teplykh.controller;

import andrey.teplykh.dto.PersonDto;
import andrey.teplykh.exception.BusinessException;
import andrey.teplykh.service.KillerService;
import lombok.AllArgsConstructor;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class KillerController {

    private final KillerService killerService;

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(killerService.getAllTeams());
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity createKiller(@RequestBody final PersonDto dto) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(killerService.createKiller(dto));
    }

    @PostMapping(path = "/teams/create/{team-id}/{team-name}/{team-size}/{start-cave-id}", produces = "application/json")
    public ResponseEntity createKillerTeam(@PathVariable(name = "team-id") final int teamId,
                                           @PathVariable(name = "team-name") final String teamName,
                                           @PathVariable(name = "team-size") final int teamSize,
                                           @PathVariable(name = "start-cave-id") final int caveId) throws BusinessException {
        return ResponseEntity.ok(killerService.createKillerTeam(teamId, teamName, teamSize, caveId));
    }

    @PostMapping(path = "/team/{team-id}/move-to-cave/{cave-id}", produces = "application/json")
    public ResponseEntity sendTeamToCave(@PathVariable(name = "team-id") final int teamId,
                                           @PathVariable(name = "cave-id") final int caveId) throws BusinessException {
        killerService.sendKillers(teamId, caveId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handle(final BusinessException e) {
        return ResponseEntity.status(e.getCode()).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handle(final Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
