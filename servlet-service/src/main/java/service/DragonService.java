package service;

import dao.Dragon;
import dto.DragonDto;
import expetions.UserDataException;
import util.OperandInfo;

import java.util.List;

public interface DragonService {
    List<DragonDto> getAll(OperandInfo orderOperand, OperandInfo[] filterOperands);

    List<Dragon> getAll();

    DragonDto save(DragonDto dto);

    DragonDto getDragonById(Long id);

    DragonDto updateElement(DragonDto newValue);

    void removeElement(Long id) throws UserDataException;

    int removeElementByCharacter(String character);

    Integer findSum();
}
