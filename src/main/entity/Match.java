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
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn(name = "second_player")
    private Player secondPlayer;

    @Column(name = "winner", nullable = false)
    private Integer winner;
}
