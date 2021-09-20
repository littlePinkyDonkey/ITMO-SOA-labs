package service.impl;

import dao.Dragon;
import dto.DragonDto;
import mapeprs.DragonMapper;
import repository.DragonRepository;
import repository.impl.DragonRepositoryImpl;
import service.CoordinatesService;
import service.DragonService;
import service.PersonService;
import util.OperandInfo;
import util.tree.FilterBinaryTree;

import java.util.ArrayList;
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
    public List<DragonDto> getAll(OperandInfo orderOperand, OperandInfo[] filterOperands) {
        FilterBinaryTree filterBinaryTree = new FilterBinaryTree();
        List<DragonDto> result = new ArrayList<>();

        if (orderOperand != null || filterOperands != null) {

            filterBinaryTree.buildTree(orderOperand, filterOperands);
            filterBinaryTree.parseQueryParams(filterBinaryTree.getRoot());

            String query = filterBinaryTree.getQuery().toString();
            if (!query.contains("order by")) {
                query = query.substring(0, query.length() - 5);
            }

            List<Dragon> dragons = dragonRepository.getAll(filterBinaryTree.getParams(), query);

            for (Dragon d : dragons) {
                result.add(dragonMapper.entityToDto(d));
            }
        } else {
            List<Dragon> dragons = dragonRepository.getAll();

            for (Dragon d : dragons) {
                result.add(dragonMapper.entityToDto(d));
            }
        }

        return result;
    }

    @Override
    public List<Dragon> getAll() {
        return dragonRepository.getAll();
    }

    @Override
    public DragonDto save(DragonDto dto) {
        Dragon dragon = dragonMapper.dtoToEntity(dto);

        if (dragon.getKiller() != null) {
            personService.save(dragon.getKiller());
        }
        coordinatesService.save(dragon.getCoordinates());
        dragonRepository.save(dragon);

        return dragonMapper.entityToDto(dragon);
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
    public DragonDto updateElement(DragonDto newValue) {
        Dragon dragon = dragonMapper.dtoToEntity(newValue);

        DragonDto updatedValue;
        try {
             updatedValue = dragonMapper.entityToDto(dragonRepository.updateElement(dragon));

            if (updatedValue == null) {
                throw new NullPointerException("Fail to update data");
            }
        } catch (NullPointerException e) {
            throw e;
        }
        return updatedValue;
    }

    @Override
    public void removeElement(Long id) {
        Dragon dragon = dragonRepository.getDragonById(id);

        dragonRepository.removeElement(id);
    }

    @Override
    public int removeElementByCharacter(String character) {
        return dragonRepository.removeElementByCharacter(character);
    }

    @Override
    public Integer findSum() {
        int sum = 0;
        List<Dragon> dragons = dragonRepository.getAll();

        for (Dragon d : dragons) {
            sum += d.getAge();
        }

        return sum;
    }
}
