package andrey.teplykh.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_teams")
public class TeamEntity {
    @Id
    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_size")
    private Integer teamSize;

    @Column(name = "killers")
    private String persons;

    @ManyToOne
    @JoinColumn(name = "cave_id")
    private CaveEntity cave;
}
