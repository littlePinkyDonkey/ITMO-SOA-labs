package dao;

import jakarta.validation.ValidationException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "COORDINATES")
public class Coordinates {
    @Id
    @Column(name = "COORDINATES_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coordinatesId;

    @Column(name = "X", nullable = false)
    private Double x; //Поле не может быть null

    @Column(name = "Y", nullable = false)
    private Integer y; //Максимальное значение поля: 350, Поле не может быть null

    public Coordinates(Double x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @PrePersist
    public void prePersist() {
        if (y >= 350) {
            throw new ValidationException("Y must be 350 or less");
        }
    }
}
