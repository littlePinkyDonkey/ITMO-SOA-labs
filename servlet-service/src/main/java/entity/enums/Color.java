package entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Color {
    GREEN("GREEN"),
    BLACK("BLACK"),
    BLUE("BLUE"),
    BROWN("BROWN");

    private String description;

    public static Color of(final String value) {
        return Stream.of(Color.values())
                .filter(type -> type.getDescription().equals(value))
                .findFirst()
                .orElse(null);
    }
}
