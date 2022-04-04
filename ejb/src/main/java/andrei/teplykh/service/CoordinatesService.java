package andrei.teplykh.service;

import andrei.teplykh.entity.Coordinates;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface CoordinatesService {
    List<Coordinates> getAll();

    void save(final Coordinates coordinates);
}
