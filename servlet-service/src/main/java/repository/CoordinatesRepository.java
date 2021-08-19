package repository;

import dao.Coordinates;

import java.util.List;

public interface CoordinatesRepository {
    List<Coordinates> getAll();

    void save(Coordinates coordinates);

    Coordinates getCoordinatesById(Integer id);

    Coordinates updateElement(Coordinates newValue);

    void removeElement(Integer id);
}
