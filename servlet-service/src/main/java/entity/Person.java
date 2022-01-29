package entity;

import entity.enums.Color;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PERSONS")
public class Person {
    @Id
    @Column(name = "PERSON_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;

    @NotBlank
    @Column(name = "NAME", nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Column(name = "PASSPORT_ID")
    private String passportID = null; //Поле может быть null

    @Column(name = "EYE_COLOR")
    @Enumerated(EnumType.STRING)
    private Color eyeColor; //Поле не может быть null

    @Column(name = "HAIR_COLOR")
    @Enumerated(EnumType.STRING)
    private Color hairColor; //Поле не может быть null

    public Person(final String name, final String passportID, final Color eyeColor, final Color hairColor) {
        this.name = name;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (name == null) {
            throw new ValidationException("Person name must not be null!");
        }

        if (name.isEmpty()) {
            throw new ValidationException("Person name must not be empty");
        }

        if (eyeColor == null || hairColor == null) {
            throw new HibernateException("Person eye color and hair color must not be null");
        }
    }
}
