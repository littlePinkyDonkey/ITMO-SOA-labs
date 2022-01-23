package mapeprs;

import dao.Dragon;
import dao.enums.Color;
import dao.enums.DragonCharacter;
import dao.enums.DragonType;
import dto.DragonDto;
import dto.PersonDto;

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
        dragon.setStringColor(Color.of(dto.getColor()).getDescription());
        dragon.setColor(Color.of(dto.getColor()));
        dragon.setStringType(DragonType.of(dto.getType()).getDescription());
        dragon.setType(DragonType.of(dto.getType()));
        dragon.setStringCharacter(DragonCharacter.of(dto.getCharacter()).getDescription());
        dragon.setCharacter(DragonCharacter.of(dto.getCharacter()));

        if (dto.getKiller().getEyeColor() != null && dto.getKiller().getEyeColor().isEmpty()) {
            dragon.setKiller(null);
        } else {
            dragon.setKiller(personMapper.dtoToEntity(dto.getKiller()));
        }

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

        if (entity.getKiller() != null) {
            dto.setKiller(personMapper.entityToDto(entity.getKiller()));
        } else {
            dto.setKiller(null);
        }

        return dto;
    }
}
