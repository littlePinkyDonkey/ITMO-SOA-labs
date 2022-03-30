package andrey.teplykh.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TeamDto {
    private Integer teamId;
    private String teamName;
    private Integer teamSize;
    private Integer startCave;
    private String persons;
}
