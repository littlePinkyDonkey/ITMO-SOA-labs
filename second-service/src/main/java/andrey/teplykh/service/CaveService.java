package andrey.teplykh.service;

import andrey.teplykh.dto.CaveDto;

import java.util.List;

public interface CaveService {
    Integer createCave();

    List<CaveDto> getAllCaves();
}
