package service;

import dao.Coordinates;

import java.util.List;

public interface CoordinatesService {
    List<Coordinates> getAll();

    void save(Coordinates coordinates);

    Coordinates getCoordinatesById(Integer id);

    Coordinates updateElement(Coordinates newValue);

    void removeElement(Integer coordinateId);
}
