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
    public static final int HIGH_CARD = 0;
    public static final int A_PAIR = 1;
    public static final int TWO_PAIR = 2;
    public static final int THREE_OF_A_KIND = 3;
    public static final int STRAIGHT = 4;
    public static final int FLUSH = 5;
    public static final int FULL_HOUSE = 6;
    public static final int FOUR_OF_A_KIND = 7;
    public static final int STRAIGHT_FLUSH = 8;

    private String[] pokers;
    private PlayerPokers player1Pokers = new PlayerPokers();
    private PlayerPokers player2Pokers = new PlayerPokers();

    public PokerGame(String[] pokers) {
        this.pokers = pokers;
        for (int i = 0; i < pokers.length; i++) {
            if (i < 5) {
                player1Pokers.getPokers().add(new Poker(pokers[i]));
            } else {
                player2Pokers.getPokers().add(new Poker(pokers[i]));
            }
        }
    }

    public String compareHighCard() {
        Collections.sort(player1Pokers.getPokers());
        Collections.sort(player2Pokers.getPokers());

        for (int i = player1Pokers.getPokers().size() - 1; i >= 0; i--) {
            if (compareNumber(i) == 1) {
                return PLAYER_1_WIN;
            } else if (compareNumber(i) == -1) {
                return PLAYER_2_WIN;
            }
        }
        return DRAW;
    }

    public void isPair(List<Poker> playerPokers) {
        for (int i = 0; i < playerPokers.size(); i++) {
            if (playerPokers.get(i).getNumber() == playerPokers.get(i+1).getNumber()) {

            }
        }
    }



    public int compareNumber(int i) {
        return player1Pokers.getPokers().get(i).compareNumber(player2Pokers.getPokers().get(i).getNumber());
    }
}
