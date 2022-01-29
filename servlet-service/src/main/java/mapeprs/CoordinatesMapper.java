package mapeprs;

import entity.Coordinates;
import dto.CoordinatesDto;

public class CoordinatesMapper {

    public Coordinates dtoToEntity(final CoordinatesDto dto) {
        return Coordinates.builder()
                .coordinatesId(dto.getCoordinatesId())
                .x(dto.getX())
                .y(dto.getY())
                .build();
    }

    public CoordinatesDto entityToDto(final Coordinates entity) {
        return CoordinatesDto.builder()
                .coordinatesId(entity.getCoordinatesId())
                .x(entity.getX())
                .y(entity.getY())
                .build();
    }
}
