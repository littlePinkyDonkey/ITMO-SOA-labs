package service.impl;

import dao.Dragon;
import dto.DragonDto;
import expetions.UserDataException;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import mapeprs.DragonMapper;
import org.hibernate.exception.ConstraintViolationException;
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
    public List<DragonDto> getAll(OperandInfo orderOperand, OperandInfo[] filterOperands, Integer page, Integer size) {
        FilterBinaryTree filterBinaryTree = new FilterBinaryTree();
        List<DragonDto> result = new ArrayList<>();
        List<Dragon> dragons;

        if (orderOperand != null || filterOperands != null) {

            filterBinaryTree.buildTree(orderOperand, filterOperands);
            filterBinaryTree.parseQueryParams(filterBinaryTree.getRoot());

            String query = filterBinaryTree.getQuery().toString();
            if (!query.contains("order by")) {
                query = query.substring(0, query.length() - 5);
            }

            dragons = dragonRepository.getAll(filterBinaryTree.getParams(), query);
        } else {
            dragons = dragonRepository.getAll();
        }

        try {
            for (int i = page * size; i < page * size + size; i++) {
                result.add(dragonMapper.entityToDto(dragons.get(i)));
            }
        } catch (IndexOutOfBoundsException e) { }

        return result;
    }

    @Override
    public List<Dragon> getAll() {
        return dragonRepository.getAll();
    }

    @Override
    public DragonDto save(DragonDto dto) {
        Dragon dragon = dragonMapper.dtoToEntity(dto);

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
        if (dragon.getKiller().getName() == null && dragon.getKiller().getEyeColor() == null && dragon.getKiller().getHairColor() == null) {
            dragon.setKiller(null);
        }
        updatedValue = dragonMapper.entityToDto(dragonRepository.updateElement(dragon));

        return updatedValue;
    }

    @Override
    public void removeElement(Long id) throws UserDataException {
        Dragon dragon = dragonRepository.getDragonById(id);

        int removed = dragonRepository.removeElement(id);

        if (removed == 0) {
            throw new UserDataException("No dragon with such id");
        }
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

    @Override
    public DragonDto getWithMinId() {
        return dragonMapper.entityToDto(dragonRepository.getWithMinId());
    }
}
