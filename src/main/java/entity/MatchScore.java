package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MatchScore {
    private Player firstPlayer;
    private Player secondPlayer;
    private Player winner;
    private String firstPlayerScore;
    private String secondPlayerScore;
    private int firstPlayerGame;
    private int secondPlayerGame;
    private int firstPlayerSet;
    private int secondPlayerSet;
}