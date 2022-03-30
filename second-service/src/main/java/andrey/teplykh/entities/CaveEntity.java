package andrey.teplykh.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_cave")
public class CaveEntity {
    @Id
    @Column(name = "cave_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer caveId;

    @OneToMany(mappedBy = "cave")
    private List<TeamEntity> teams = new ArrayList<>();
}
