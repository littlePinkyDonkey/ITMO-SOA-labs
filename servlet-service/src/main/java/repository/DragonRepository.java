package repository;

import dao.Dragon;

import java.util.List;
import java.util.Map;

public interface DragonRepository {
    List<Dragon> getAll(Map<String, String> parameters, String query);

    void save(Dragon dragon);

    Dragon getDragonById(Long id);

    Dragon updateElement(Dragon newValue);

    void removeElement(Long id);
}
