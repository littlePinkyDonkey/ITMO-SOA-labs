package andrei.teplykh.service;

import andrei.teplykh.entity.Coordinates;
import andrei.teplykh.repository.CoordinatesRepository;
import andrei.teplykh.repository.CoordinatesRepositoryIml;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote(CoordinatesService.class)
public class CoordinatesServiceImpl implements CoordinatesService {

    @EJB
    private CoordinatesRepository coordinatesRepositoryIml;

    @Override
    public List<Coordinates> getAll() {
        return coordinatesRepositoryIml.getAll();
    }

    @Override
    public void save(final Coordinates coordinates) {
        coordinatesRepositoryIml.save(coordinates);
    }
}
