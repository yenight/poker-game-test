package poker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PokerTest {

    @Test
    public void highCard_should_return_true_when_player1_is_6D_and_player2_is_4C () {
        //given
        Poker poker1 = new Poker("5H");
        Poker poker2 = new Poker("6S");
        //when
        boolean result = poker1.compareNumber(poker2.getNumber());
        //then
        assertTrue(result);
    }

}
