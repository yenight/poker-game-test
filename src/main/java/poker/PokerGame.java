package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerGame {

    private String[] pokers;
    private List<Poker> player1Pokers;
    private List<Poker> player2Pokers;

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
//        Collections.sort(player1);
//        Collections.sort(player2);
//
//        for (int i = player1.size() - 1; i <= 0; i--) {
//            if (player1.get(i).equals(player2.get(i))) {
//
//            }
//        }


        return null;
    }
}
