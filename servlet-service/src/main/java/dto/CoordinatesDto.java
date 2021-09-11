package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesDto {
    private Integer coordinatesId;
    private Double x; //Поле не может быть null
    private Integer y; //Максимальное значение поля: 350, Поле не может быть null
}
