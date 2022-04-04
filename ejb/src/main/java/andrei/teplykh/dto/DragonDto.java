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
public class DragonDto implements Serializable {
    private Long id;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private CoordinatesDto coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer age = null; //Значение поля должно быть больше 0, Поле может быть null
    private String color;
    private String type;
    private String character;
    private PersonDto killer; //Поле может быть null
}
