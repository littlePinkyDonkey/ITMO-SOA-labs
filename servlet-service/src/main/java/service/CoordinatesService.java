package service;

import entity.Coordinates;
import repository.CoordinatesRepository;

import javax.inject.Inject;
import java.util.List;

public class CoordinatesService {

    @Inject
    private CoordinatesRepository coordinatesRepository;

    public List<Coordinates> getAll() {
        return coordinatesRepository.getAll();
    }

    public void save(final Coordinates coordinates) {
        coordinatesRepository.save(coordinates);
    }

    public Coordinates getCoordinatesById(final Integer id) {
        return coordinatesRepository.getCoordinatesById(id);
    }

    public Coordinates updateElement(final Coordinates newValue) {
        return coordinatesRepository.updateElement(newValue);
    }

    public void removeElement(final Integer id) {
        coordinatesRepository.removeElement(id);
    }
}
