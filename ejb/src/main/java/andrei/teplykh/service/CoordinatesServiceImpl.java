package andrei.teplykh.service;

import andrei.teplykh.entity.Coordinates;
import andrei.teplykh.repository.CoordinatesRepositoryIml;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Remote(CoordinatesService.class)
public class CoordinatesServiceImpl implements CoordinatesService {

    @Inject
    private CoordinatesRepositoryIml coordinatesRepositoryIml;

    @Override
    public List<Coordinates> getAll() {
        return coordinatesRepositoryIml.getAll();
    }

    @Override
    public void save(final Coordinates coordinates) {
        coordinatesRepositoryIml.save(coordinates);
    }
}
