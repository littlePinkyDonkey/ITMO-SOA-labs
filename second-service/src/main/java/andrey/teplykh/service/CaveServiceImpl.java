package andrey.teplykh.service;

import andrey.teplykh.dto.CaveDto;
import andrey.teplykh.entities.CaveEntity;
import andrey.teplykh.mapper.CaveMapper;
import andrey.teplykh.repository.CaveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CaveServiceImpl implements CaveService {

    private final CaveRepository repository;
    private final CaveMapper mapper;

    @Override
    public Integer createCave() {
        final CaveEntity cave = CaveEntity.builder().build();
        return repository.save(cave).getCaveId();
    }

    @Override
    public List<CaveDto> getAllCaves() {
        final List<CaveEntity> caves = repository.findAll();
        final List<CaveDto> response = new ArrayList<>();

        for (CaveEntity cave : caves) {
            response.add(mapper.entityToDto(cave));
        }

        return response;
    }
}
