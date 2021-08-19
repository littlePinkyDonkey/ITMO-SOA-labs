package repository;

import dao.Dragon;

import java.util.List;

public interface DragonRepository {
    List<Dragon> getAll();

    void save(Dragon dragon);
}
