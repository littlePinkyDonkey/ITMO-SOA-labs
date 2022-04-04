package andrei.teplykh.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum FilterOperands implements Serializable {
    DRAGON_ID("dragon_id", "id"),
    DRAGON_NAME("dragon_name", "name"),
    COORDINATE_X("coordinate_x", "coordinates.x"),
    COORDINATE_Y("coordinate_y", "coordinates.y"),
    DRAGON_CREATION_DATE("dragon_creation_date", "creationDate"),
    DRAGON_AGE("dragon_age", "age"),
    DRAGON_COLOR("dragon_color", "color"),
    DRAGON_TYPE("dragon_type", "type"),
    DRAGON_CHARACTER("dragon_character", "character"),
    KILLER_NAME("killer_name", "killer.name"),
    KILLER_PASSPORT_ID("killer_passport", "killer.passportID"),
    KILLER_EYE_COLOR("killer_eye", "killer.eyeColor"),
    KILLER_HAIR_COLOR("killer_hair", "killer.hairColor");

    private String operand;
    private String filedName;

    public static FilterOperands of(final String value) {
        return Stream.of(FilterOperands.values())
                .filter(element -> element.getOperand().equals(value))
                .findFirst()
                .orElse(null);
    }
}
