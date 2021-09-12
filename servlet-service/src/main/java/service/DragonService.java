package service;

import dto.DragonDto;
import util.OperandInfo;
import util.enums.FilterOperands;

import java.util.List;

public interface DragonService {
    List<DragonDto> getAll(OperandInfo orderOperand, OperandInfo[] filterOperands);

    void save(DragonDto dto);

    DragonDto getDragonById(Long id);

    DragonDto updateElement(DragonDto newValue);

    void removeElement(Long id);
}
