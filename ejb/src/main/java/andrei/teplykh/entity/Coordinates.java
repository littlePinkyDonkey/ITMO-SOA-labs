package andrei.teplykh.entity;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public Coordinates(final Double x, final Integer y) {
        this.x = x;
        this.y = y;
    }

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (y != null) {
            if (y >= 350) {
                throw new ValidationException("Y must be 350 or less");
            }
        } else {
            throw new ValidationException("Y must not be empty");
        }

        if (x == null) {
            throw new ValidationException("X must not be empty");
        }
    }
}
