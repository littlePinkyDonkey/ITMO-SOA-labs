package dao;

import dao.enums.Color;
import jakarta.validation.ValidationException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Data
@NoArgsConstructor
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

    @Transient
    private Color eyeColor; //Поле не может быть null
    @Column(name = "EYE_COLOR", nullable = false)
    private String stringEyeColor;

    @Transient
    private Color hairColor; //Поле не может быть null
    @Column(name = "HAIR_COLOR", nullable = false)
    private String stringHairColor;

    public Person(String name, String passportID, Color eyeColor, Color hairColor) {
        this.name = name;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    @PrePersist
    public void prePersist() {
        if (name == null) {
            throw new ValidationException("Person name must not be null!");
        }

        if (name.isEmpty()) {
            throw new ValidationException("Person name must not be empty");
        }

        if (eyeColor != null && hairColor != null) {
            this.stringEyeColor = eyeColor.getDescription();
            this.stringHairColor = hairColor.getDescription();
        } else {
            throw new HibernateException("Person eye color and hair color must not be null");
        }
    }

    @PostLoad
    public void postLoad() {
        this.eyeColor = Color.of(stringEyeColor);
        this.hairColor = Color.of(stringHairColor);
    }
}
