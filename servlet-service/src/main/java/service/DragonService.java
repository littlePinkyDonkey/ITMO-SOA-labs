package service;

import com.google.gson.Gson;
import entity.Dragon;
import dto.DragonDto;
import entity.enums.DragonCharacter;
import expetions.BusinessException;
import expetions.UserDataException;
import mapeprs.DragonMapper;
import repository.DragonRepository;
import util.GsonQualifier;
import util.OperandInfo;
import util.tree.FilterBinaryTree;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DragonService {

    @Inject
    private DragonRepository dragonRepository;

    @Inject
    private CoordinatesService coordinatesService;

    @Inject
    private PersonService personService;

    @Inject
    private DragonMapper dragonMapper;

    @Inject
    @GsonQualifier
    private Gson gson;

    public String getAll(final OperandInfo orderOperand, final OperandInfo[] filterOperands,
                                  final Integer page, final Integer size) throws BusinessException {
        final FilterBinaryTree filterBinaryTree = new FilterBinaryTree();
        final List<DragonDto> result = new ArrayList<>();
        final List<Dragon> dragons;

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
        } catch (final IndexOutOfBoundsException e) { }

        return gson.toJson(result);
    }

    public String save(final String request) throws BusinessException {
        final Dragon dragon = dragonMapper.dtoToEntity(gson.fromJson(request, DragonDto.class));

        dragonRepository.save(dragon);

        return gson.toJson(dragon);
    }

    public String getDragonById(final Long id) throws BusinessException {
        final Dragon dragon = dragonRepository.getDragonById(id);

        if (dragon != null) {
            return gson.toJson(dragonMapper.entityToDto(dragon));
        } else {
            return null;
        }
    }

    public String updateElement(final String newValue) throws BusinessException {
        final Dragon dragon = dragonMapper.dtoToEntity(gson.fromJson(newValue, DragonDto.class));

        DragonDto updatedValue;
        if (dragon.getKiller().getName() == null && dragon.getKiller().getEyeColor() == null && dragon.getKiller().getHairColor() == null) {
            dragon.setKiller(null);
        }
        updatedValue = dragonMapper.entityToDto(dragonRepository.updateElement(dragon));

        return gson.toJson(updatedValue);
    }

    public void removeElement(final Long id) throws UserDataException, BusinessException {
        final Dragon dragon = dragonRepository.getDragonById(id);

        final int removed = dragonRepository.removeElement(id);

        if (removed == 0) {
            throw new UserDataException("No dragon with such id");
        }
    }

    public int removeElementByCharacter(final String character) throws BusinessException {
        return dragonRepository.removeElementByCharacter(DragonCharacter.valueOf(character));
    }

    public Integer findSum() throws BusinessException {
        int sum = 0;
        final List<Dragon> dragons = dragonRepository.getAll();

        for (Dragon d : dragons) {
            sum += d.getAge();
        }

        return sum;
    }

    public String getWithMinId() throws BusinessException {
        return gson.toJson(dragonMapper.entityToDto(dragonRepository.getWithMinId()));
    }
}
