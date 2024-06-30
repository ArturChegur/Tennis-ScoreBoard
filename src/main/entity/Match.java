package main.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "first_player")
    private Integer FirstPlayer;

    @ManyToOne
    @JoinColumn(name = "second_player")
    private Integer SecondPlayer;

    @Column(name = "winner", nullable = false)
    private Integer winner;
}
