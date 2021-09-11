package mapeprs;

import dao.Dragon;
import dao.enums.Color;
import dao.enums.DragonCharacter;
import dao.enums.DragonType;
import dto.DragonDto;

public class DragonMapper {
    private final CoordinatesMapper coordinatesMapper = new CoordinatesMapper();
    private final PersonMapper personMapper = new PersonMapper();

    public Dragon dtoToEntity(DragonDto dto) {
        Dragon dragon = new Dragon();

        dragon.setId(dto.getId());
        dragon.setName(dto.getName());
        dragon.setCoordinates(coordinatesMapper.dtoToEntity(dto.getCoordinates()));
        dragon.setCreationDate(dto.getCreationDate());
        dragon.setAge(dto.getAge());
        dragon.setColor(Color.of(dto.getColor()));
        dragon.setType(DragonType.of(dto.getType()));
        dragon.setCharacter(DragonCharacter.of(dto.getCharacter()));
        dragon.setKiller(personMapper.dtoToEntity(dto.getKiller()));

        return dragon;
    }

    public DragonDto entityToDto(Dragon entity) {
        DragonDto dto = new DragonDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCoordinates(coordinatesMapper.entityToDto(entity.getCoordinates()));
        dto.setCreationDate(entity.getCreationDate());
        dto.setAge(entity.getAge());
        dto.setColor(entity.getStringColor());
        dto.setType(entity.getStringType());
        dto.setCharacter(entity.getStringCharacter());
        dto.setKiller(personMapper.entityToDto(entity.getKiller()));

        return dto;
    }
}
