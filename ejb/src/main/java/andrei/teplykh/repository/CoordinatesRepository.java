package andrei.teplykh.repository;

import andrei.teplykh.entity.Coordinates;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

@Local
public interface CoordinatesRepository {
    public List<Coordinates> getAll();

    public void save(final Coordinates coordinates);


}
