package andrey.teplykh.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CaveDto {
    private Integer caveId;
    private List<TeamDto> teams;
}
