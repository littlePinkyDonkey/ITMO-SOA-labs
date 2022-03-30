package andrey.teplykh.repository;

import andrey.teplykh.entities.CaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaveRepository extends JpaRepository<CaveEntity, Integer> {
    CaveEntity getCaveEntityByCaveId(final Integer caveId);
}
