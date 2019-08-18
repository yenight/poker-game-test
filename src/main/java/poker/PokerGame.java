package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerGame {

    public static final String PLAYER_2_WIN = "player2 win";
    public static final String PLAYER_1_WIN = "player1 win";
    public static final String DRAW = "draw";

    private String[] pokers;
    private List<Poker> player1Pokers = new ArrayList<>();
    private List<Poker> player2Pokers = new ArrayList<>();

    public PokerGame(String[] pokers) {
        this.pokers = pokers;
        for (int i = 0; i < pokers.length; i++) {
            if (i < 5) {
                player1Pokers.add(new Poker(pokers[i]));
            } else {
                player2Pokers.add(new Poker(pokers[i]));
            }
        }
    }

    public String compareHighCard() {
        Collections.sort(player1Pokers);
        Collections.sort(player2Pokers);

        for (int i = player1Pokers.size() - 1; i >= 0; i--) {
            if (compareNumber(i) == 1) {
                return PLAYER_1_WIN;
            } else if (compareNumber(i) == -1) {
                return PLAYER_2_WIN;
            }
        }
        return DRAW;
    }

    public int compareNumber(int i) {
        return player1Pokers.get(i).compareNumber(player2Pokers.get(i).getNumber());
    }
}
