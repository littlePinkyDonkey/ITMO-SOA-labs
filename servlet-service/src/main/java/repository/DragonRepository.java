package repository;

import dao.Dragon;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Map;

public interface DragonRepository {
    List<Dragon> getAll(Map<String, String> parameters, String query);

    List<Dragon> getAll();

    void save(Dragon dragon) throws ValidationException, ConstraintViolationException;

    Dragon getDragonById(Long id);

    Dragon updateElement(Dragon newValue);

    int removeElement(Long id);

    int removeElementByCharacter(String character);

    Dragon getWithMinId();
}
