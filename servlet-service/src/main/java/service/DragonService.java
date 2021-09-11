package service;

import dao.Dragon;
import dto.DragonDto;

import java.util.List;

public interface DragonService {
    List<Dragon> getAll();

    void save(DragonDto dto);

    DragonDto getDragonById(Long id);

    void updateElement(DragonDto newValue);

    void removeElement(Long id);
}
