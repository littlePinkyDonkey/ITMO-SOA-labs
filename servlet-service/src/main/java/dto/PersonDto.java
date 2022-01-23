package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Integer personId;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportId = null; //Поле может быть null
    private String eyeColor;
    private String hairColor;
}
