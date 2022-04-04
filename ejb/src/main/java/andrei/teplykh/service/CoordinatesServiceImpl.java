package andrei.teplykh.service;

import andrei.teplykh.entity.Coordinates;
import andrei.teplykh.repository.CoordinatesRepository;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Remote(CoordinatesService.class)
public class CoordinatesServiceImpl implements CoordinatesService {

    @Inject
    private CoordinatesRepository coordinatesRepository;

    @Override
    public List<Coordinates> getAll() {
        return coordinatesRepository.getAll();
    }

    @Override
    public void save(final Coordinates coordinates) {
        coordinatesRepository.save(coordinates);
    }
}
