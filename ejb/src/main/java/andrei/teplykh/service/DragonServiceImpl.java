package andrei.teplykh.service;

import andrei.teplykh.entity.Dragon;
import andrei.teplykh.dto.DragonDto;
import andrei.teplykh.entity.enums.DragonCharacter;
import andrei.teplykh.exception.BusinessException;
import andrei.teplykh.exception.UserDataException;
import andrei.teplykh.mappers.DragonMapper;
import andrei.teplykh.repository.DragonRepositoryImpl;
import andrei.teplykh.util.OperandInfo;
import andrei.teplykh.util.tree.FilterBinaryTree;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Remote(DragonService.class)
public class DragonServiceImpl implements DragonService {

    @Inject
    private DragonRepositoryImpl dragonRepositoryImpl;

    private DragonMapper dragonMapper = new DragonMapper();

    //private Gson gson = GsonProvider.gson;

    @Override
    public List<DragonDto> getAll(final OperandInfo orderOperand, final OperandInfo[] filterOperands,
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

            dragons = dragonRepositoryImpl.getAll(filterBinaryTree.getParams(), query);
        } else {
            dragons = dragonRepositoryImpl.getAll();
        }

        try {
            for (int i = page * size; i < page * size + size; i++) {
                result.add(dragonMapper.entityToDto(dragons.get(i)));
            }
        } catch (final IndexOutOfBoundsException e) { }

        return result;
    }

    @Override
    public DragonDto save(final DragonDto request) throws BusinessException {
        final Dragon dragon = dragonMapper.dtoToEntity(request);

        dragonRepositoryImpl.save(dragon);

        return dragonMapper.entityToDto(dragon);
    }

    @Override
    public DragonDto getDragonById(final Long id) throws BusinessException {
        final Dragon dragon = dragonRepositoryImpl.getDragonById(id);

        if (dragon != null) {
            return dragonMapper.entityToDto(dragon);
        } else {
            return null;
        }
    }

    @Override
    public DragonDto updateElement(final DragonDto newValue) throws BusinessException {
        final Dragon dragon = dragonMapper.dtoToEntity(newValue);

        DragonDto updatedValue;
        if (dragon.getKiller().getName() == null && dragon.getKiller().getEyeColor() == null && dragon.getKiller().getHairColor() == null) {
            dragon.setKiller(null);
        }
        updatedValue = dragonMapper.entityToDto(dragonRepositoryImpl.updateElement(dragon));

        return updatedValue;
    }

    @Override
    public void removeElement(final Long id) throws UserDataException, BusinessException {
        final Dragon dragon = dragonRepositoryImpl.getDragonById(id);

        final int removed = dragonRepositoryImpl.removeElement(id);

        if (removed == 0) {
            throw new UserDataException("No dragon with such id");
        }
    }

    @Override
    public int removeElementByCharacter(final String character) throws BusinessException {
        return dragonRepositoryImpl.removeElementByCharacter(DragonCharacter.valueOf(character));
    }

    @Override
    public Integer findSum() throws BusinessException {
        int sum = 0;
        final List<Dragon> dragons = dragonRepositoryImpl.getAll();

        for (Dragon d : dragons) {
            sum += d.getAge();
        }

        return sum;
    }

    @Override
    public DragonDto getWithMinId() throws BusinessException {
        return dragonMapper.entityToDto(dragonRepositoryImpl.getWithMinId());
    }
}
