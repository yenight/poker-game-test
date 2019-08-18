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
        this.addPlayerPoker(pokers);
    }

    public String compare() {
        String result = "";
        if (player1Pokers.getStatus() > player2Pokers.getStatus()) {
            result = PLAYER_1_WIN;
        } else if (player1Pokers.getStatus() < player2Pokers.getStatus()) {
            result = PLAYER_2_WIN;
        } else {
            System.out.println();
            switch (player1Pokers.getStatus()) {
                case 0:
                    result = compareHighCard();
                    break;
                case 1:
                    result = comparePair();
                    break;
            }
        }
        return result;
    }

    public String compareHighCard() {
        for (int i = player1Pokers.getPokers().size() - 1; i >= 0; i--) {
            if (compareNumber(i, i) == 1) {
                return PLAYER_1_WIN;
            } else if (compareNumber(i, i) == -1) {
                return PLAYER_2_WIN;
            }
        }
        return DRAW;
    }

    public String comparePair() {
        for (int i = 0; i < player1Pokers.getPokers().size() - 1; i++) {
            if (player1Pokers.getPokers().get(i).getNumber() == player1Pokers.getPokers().get(i+1).getNumber()) {
                for (int j = 0; j < player2Pokers.getPokers().size() - 1; j++) {
                    if (player2Pokers.getPokers().get(j).getNumber() == player2Pokers.getPokers().get(j+1).getNumber()) {
                        if (compareNumber(i, j) == 1) {
                            return PLAYER_1_WIN;
                        } else if (compareNumber(i, j) == -1) {
                            return PLAYER_2_WIN;
                        }
                    }
                }
            }
        }
        return compareHighCard();
    }

    public void isPair(PlayerPokers playerPokers) {
        int count = 0;
        for (int i = 0; i < playerPokers.getPokers().size() - 1; i++) {
            if (playerPokers.getPokers().get(i).getNumber() == playerPokers.getPokers().get(i+1).getNumber()) {
                count++;
            }
        }
        if (count == 1) {
            playerPokers.setStatus(A_PAIR);
        } else if (count == 2){
            playerPokers.setStatus(TWO_PAIR);
        }
    }

    public int compareNumber(int i, int j) {
        return player1Pokers.getPokers().get(i).compareNumber(player2Pokers.getPokers().get(j).getNumber());
    }

    public void addPlayerPoker(String[] pokers) {
        for (int i = 0; i < pokers.length; i++) {
            if (i < 5) {
                player1Pokers.getPokers().add(new Poker(pokers[i]));
            } else {
                player2Pokers.getPokers().add(new Poker(pokers[i]));
            }
        }
        Collections.sort(player1Pokers.getPokers());
        Collections.sort(player2Pokers.getPokers());

        isPair(player1Pokers);
        isPair(player2Pokers);
    }
}
