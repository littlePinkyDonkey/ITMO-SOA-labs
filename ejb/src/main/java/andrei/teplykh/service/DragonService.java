package andrei.teplykh.service;

import andrei.teplykh.dto.DragonDto;
import andrei.teplykh.entity.Dragon;
import andrei.teplykh.exception.BusinessException;
import andrei.teplykh.exception.UserDataException;
import andrei.teplykh.util.OperandInfo;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface DragonService {
    List<DragonDto> getAll(final OperandInfo orderOperand, final OperandInfo[] filterOperands,
                           final Integer page, final Integer size) throws BusinessException;

    DragonDto save(final DragonDto request) throws BusinessException;

    DragonDto getDragonById(final Long id) throws BusinessException;

    DragonDto updateElement(final DragonDto newValue) throws BusinessException;

    void removeElement(final Long id) throws UserDataException, BusinessException;

    int removeElementByCharacter(final String character) throws BusinessException;

    Integer findSum() throws BusinessException;

    DragonDto getWithMinId() throws BusinessException;
}
