package mapeprs;

import dao.Coordinates;
import dto.CoordinatesDto;

public class CoordinatesMapper {

    public Coordinates dtoToEntity(CoordinatesDto dto) {
        Coordinates coordinates = new Coordinates();

        coordinates.setCoordinatesId(dto.getCoordinatesId());
        coordinates.setX(dto.getX());
        coordinates.setY(dto.getY());

        return coordinates;
    }

    public CoordinatesDto entityToDto(Coordinates entity) {
        CoordinatesDto coordinatesDto = new CoordinatesDto();

        coordinatesDto.setCoordinatesId(entity.getCoordinatesId());
        coordinatesDto.setX(entity.getX());
        coordinatesDto.setY(entity.getY());

        return coordinatesDto;
    }
}
