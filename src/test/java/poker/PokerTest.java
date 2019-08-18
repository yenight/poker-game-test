package poker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PokerTest {

    @Test
    public void highCard_should_return_true_when_player1_is_3S_and_player2_is_7D () {
        //given
        Poker poker1 = new Poker("3S");
        Poker poker2 = new Poker("7D");
        //when
        boolean result = poker1.compareNumber(poker2.getNumber());
        //then
        assertFalse(result);
    }

}
