package andrei.teplykh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesDto implements Serializable {
    private Integer coordinatesId;
    private Double x; //Поле не может быть null
    private Integer y; //Максимальное значение поля: 350, Поле не может быть null
}
