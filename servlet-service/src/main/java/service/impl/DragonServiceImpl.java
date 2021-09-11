package service.impl;

import dao.Dragon;
import dto.DragonDto;
import mapeprs.DragonMapper;
import repository.DragonRepository;
import repository.impl.DragonRepositoryImpl;
import service.CoordinatesService;
import service.DragonService;
import service.PersonService;

import java.util.List;

public class DragonServiceImpl implements DragonService {
    private final DragonRepository dragonRepository;

    private final CoordinatesService coordinatesService;
    private final PersonService personService;

    private final DragonMapper dragonMapper;

    private static volatile DragonServiceImpl instance;
    private DragonServiceImpl() {
        this.dragonRepository = new DragonRepositoryImpl();
        this.coordinatesService = CoordinatesServiceImpl.getInstance();
        this.personService = PersonServiceImpl.getInstance();
        this.dragonMapper = new DragonMapper();
    }

    public static DragonServiceImpl getInstance() {
        if (instance == null) {
            synchronized (DragonServiceImpl.class) {
                if (instance == null) {
                    instance = new DragonServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Dragon> getAll() {
        return null;
    }

    @Override
    public void save(DragonDto dto) {
        Dragon dragon = dragonMapper.dtoToEntity(dto);

        if (dragon.getKiller() != null) {
            personService.save(dragon.getKiller());
        }
        coordinatesService.save(dragon.getCoordinates());
        dragonRepository.save(dragon);
    }

    @Override
    public DragonDto getDragonById(Long id) {
        Dragon dragon = dragonRepository.getDragonById(id);

        if (dragon != null) {
            return dragonMapper.entityToDto(dragon);
        } else {
            return null;
        }
    }

    @Override
    public void updateElement(DragonDto newValue) {
        removeElement(newValue.getId());
        save(newValue);
    }

    @Override
    public void removeElement(Long id) {
        Dragon dragon = dragonRepository.getDragonById(id);

        dragonRepository.removeElement(id);
        coordinatesService.removeElement(dragon.getCoordinates().getCoordinatesId());
        personService.removeElement(id, dragon.getKiller().getPersonId());
    }
}
