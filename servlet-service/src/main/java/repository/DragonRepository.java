package repository;

import dao.Dragon;

import java.util.List;
import java.util.Map;

public interface DragonRepository {
    List<Dragon> getAll(Map<String, String> parameters, String query);

    List<Dragon> getAll();

    void save(Dragon dragon);

    Dragon getDragonById(Long id);

    Dragon updateElement(Dragon newValue);

    int removeElement(Long id);

    int removeElementByCharacter(String character);
}
