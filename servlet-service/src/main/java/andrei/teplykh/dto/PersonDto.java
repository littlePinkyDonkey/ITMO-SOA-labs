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
public class PersonDto implements Serializable {
    private Integer personId;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportId = null; //Поле может быть null
    private String eyeColor;
    private String hairColor;
}
