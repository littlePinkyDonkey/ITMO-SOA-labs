package entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum DragonType {
    WATER("WATER"),
    UNDERGROUND("UNDERGROUND"),
    FIRE("FIRE");

    private String description;

    public static DragonType of(final String value) {
        return Stream.of(DragonType.values())
                .filter(type -> type.getDescription().equals(value))
                .findFirst()
                .orElse(null);
    }
}
