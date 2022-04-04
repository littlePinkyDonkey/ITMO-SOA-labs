package andrei.teplykh.repository;

import andrei.teplykh.entity.Dragon;
import andrei.teplykh.entity.enums.DragonCharacter;
import andrei.teplykh.exception.BusinessException;
import jakarta.validation.ValidationException;
import org.hibernate.exception.ConstraintViolationException;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface DragonRepository {
    public List<Dragon> getAll(final Map<String, String> parameters, final String stringQuery) throws BusinessException;

    public List<Dragon> getAll() throws BusinessException;

    public void save(final Dragon dragon) throws BusinessException;

    public Dragon getDragonById(final Long id) throws BusinessException;

    public Dragon updateElement(final Dragon newValue) throws BusinessException;

    public int removeElement(final Long id);

    public int removeElementByCharacter(final DragonCharacter character) throws BusinessException;

    public Dragon getWithMinId() throws BusinessException;


}
