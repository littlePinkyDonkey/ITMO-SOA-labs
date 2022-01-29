package mapeprs;

import entity.Dragon;
import entity.enums.Color;
import entity.enums.DragonCharacter;
import entity.enums.DragonType;
import dto.DragonDto;

import javax.inject.Inject;

public class DragonMapper {

    @Inject
    private CoordinatesMapper coordinatesMapper;

    @Inject
    private PersonMapper personMapper;

    public Dragon dtoToEntity(final DragonDto dto) {
        final Dragon dragon = Dragon.builder()
                .id(dto.getId())
                .name(dto.getName())
                .coordinates(coordinatesMapper.dtoToEntity(dto.getCoordinates()))
                .creationDate(dto.getCreationDate())
                .age(dto.getAge())
                .color(Color.of(dto.getColor()))
                .type(DragonType.of(dto.getType()))
                .character(DragonCharacter.of(dto.getCharacter()))
                .killer(personMapper.dtoToEntity(dto.getKiller()))
                .build();

        if (dto.getKiller().getEyeColor() != null && dto.getKiller().getEyeColor().isEmpty()) {
            dragon.setKiller(null);
        } else {
            dragon.setKiller(personMapper.dtoToEntity(dto.getKiller()));
        }

        return dragon;
    }

    public DragonDto entityToDto(final Dragon entity) {
        final DragonDto dto = DragonDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .coordinates(coordinatesMapper.entityToDto(entity.getCoordinates()))
                .creationDate(entity.getCreationDate())
                .age(entity.getAge())
                .color(entity.getColor().getDescription())
                .type(entity.getType().getDescription())
                .character(entity.getCharacter().getDescription())
                .build();

        if (entity.getKiller() != null) {
            dto.setKiller(personMapper.entityToDto(entity.getKiller()));
        } else {
            dto.setKiller(null);
        }

        return dto;
    }
}
