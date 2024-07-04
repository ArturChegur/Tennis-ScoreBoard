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

    @ManyToOne
    @JoinColumn(name = "winner")
    private Player winner;

    @Transient
    private MatchScore matchScore;

    @Getter
    @Setter
    public static class MatchScore {
        private int firstPlayerScore = 1;
        private int secondPlayerScore = 2;
        private int firstPlayerGame = 3;
        private int secondPlayerGame = 4;
        private int firstPlayerSet = 5;
        private int secondPlayerSet = 6;
    }
}
