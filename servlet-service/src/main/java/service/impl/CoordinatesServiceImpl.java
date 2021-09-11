package service.impl;

import dao.Coordinates;
import dao.Dragon;
import repository.CoordinatesRepository;
import repository.impl.CoordinatesRepositoryImpl;
import service.CoordinatesService;
import service.DragonService;

import java.util.List;

public class CoordinatesServiceImpl implements CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;

    private static volatile CoordinatesServiceImpl instance;
    private CoordinatesServiceImpl() {
        this.coordinatesRepository = new CoordinatesRepositoryImpl();
    }

    public static CoordinatesServiceImpl getInstance() {
        if (instance == null) {
            synchronized (CoordinatesServiceImpl.class) {
                if (instance == null) {
                    instance = new CoordinatesServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Coordinates> getAll() {
        return coordinatesRepository.getAll();
    }

    @Override
    public void save(Coordinates coordinates) {
        coordinatesRepository.save(coordinates);
    }

    @Override
    public Coordinates getCoordinatesById(Integer id) {
        return coordinatesRepository.getCoordinatesById(id);
    }

    @Override
    public Coordinates updateElement(Coordinates newValue) {
        return coordinatesRepository.updateElement(newValue);
    }

    @Override
    public void removeElement(Integer id) {
        coordinatesRepository.removeElement(id);
    }
}
