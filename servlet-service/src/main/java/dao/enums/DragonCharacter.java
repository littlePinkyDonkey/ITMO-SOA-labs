package dao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum DragonCharacter {
    CUNNING("CUNNING"),
    WISE("WISE"),
    CHAOTIC_EVIL("CHAOTIC_EVIL");

    private String description;

    public static DragonCharacter of(String value) {
        return Stream.of(DragonCharacter.values())
                .filter(type -> type.getDescription().equals(value))
                .findFirst()
                .orElse(null);
    }
}
