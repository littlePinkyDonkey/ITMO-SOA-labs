package andrei.teplykh.entity;

import andrei.teplykh.entity.enums.Color;
import andrei.teplykh.entity.enums.DragonCharacter;
import andrei.teplykh.entity.enums.DragonType;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DRAGONS")
public class Dragon {
    @Id
    @Column(name = "DRAGON_ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "NAME", nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "COORDINATES_ID")
    private Coordinates coordinates; //Поле не может быть null

    @Column(name = "CREATION_DATE", nullable = false)
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(name = "AGE")
    private Integer age = null; //Значение поля должно быть больше 0, Поле может быть null

    @Enumerated(EnumType.STRING)
    private Color color = null; //Поле может быть null

    @Enumerated(EnumType.STRING)
    private DragonType type; //Поле не может быть null

    @Enumerated(EnumType.STRING)
    private DragonCharacter character; //Поле не может быть null

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "PERSON_ID")
    private Person killer; //Поле может быть null


    public Dragon(final String name, final Integer age, final Color color, final DragonType type, final DragonCharacter character) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
    }

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (name == null) {
            throw new ValidationException("Dragon name must not be null!");
        }

        if (name.isEmpty()) {
            throw new ValidationException("Dragon name must not be empty");
        }

        if (creationDate == null) {
            this.creationDate = LocalDateTime.now();
        }

        if (age != null) {
            if (age <= 0) {
                throw new ValidationException("Dragon age must be bigger than 0");
            }
        }

        if (coordinates == null) {
            throw new ValidationException("Dragon coordinates must not be null!");
        }

        if (type == null || character == null) {
            throw new ValidationException("Dragon type and character must not be null!");
        }
    }
}
