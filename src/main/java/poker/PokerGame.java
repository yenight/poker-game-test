package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PokerGame {

    public PokerGame() {
    }

    public static String compare(List<String> player1, List<String> player2) {
        Collections.sort(player1);
        Collections.sort(player2);

        for (int i = player1.size() - 1; i <= 0; i--) {
            if (player1.get(i).equals(player2.get(i))) {

            }
        }


        return null;
    }

    public static boolean compareNumber(char a, char b) {
        if (('0' <= a && a <= '9') || ('0' <= b && b <= '9')) {
            return a < b;
        } else {
            switch (a) {
                case 'T':
            }
        }
        return a < b;
    }
}
