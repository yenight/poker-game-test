package poker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PokerTest {

    @Test
    public void highCard_should_return_negative_1_when_player1_is_3S_and_player2_is_7D () {
        //given
        Poker poker1 = new Poker("3S");
        Poker poker2 = new Poker("7D");

        //when
        int result = poker1.compareNumber(poker2.getNumber());

        //then
        assertEquals(-1, result);
    }

    @Test
    public void highCard_should_return_0_when_player1_is_2S_and_player2_is_2D () {
        //given
        Poker poker1 = new Poker("2S");
        Poker poker2 = new Poker("2D");

        //when
        int result = poker1.compareNumber(poker2.getNumber());

        //then
        assertEquals(0, result);
    }

    @Test
    public void highCard_should_return_player1_win_when_input_3S_4D_JH_6S_9S_6S_3C_TC_9C_5C () {
        //given
        String[] pokers = {"3S", "4D", "JH", "6S", "9S", "6S", "3C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player1 win", result);
    }

    @Test
    public void highCard_should_return_player2_win_when_input_3S_4D_TH_6S_9S_6S_3C_TC_9C_5C () {
        //given
        String[] pokers = {"3S", "4D", "TH", "6S", "9S", "6S", "3C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }

    @Test
    public void highCard_should_return_draw_when_input_3S_4D_TH_5S_9S_3H_4C_TC_9C_5C () {
        //given
        String[] pokers = {"3S", "4D", "TH", "5S", "9S", "3H", "4C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("draw", result);
    }

    @Test
    public void highCard_compare_pair_should_return_player1_win_when_input_AS_AD_3H_6S_9S_2S_6C_TC_9C_5C () {
        //given
        String[] pokers = {"AS", "AD", "3H", "6S", "9S", "2S", "6C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player1 win", result);
    }

    @Test
    public void pair_should_return_player2_win_when_input_3S_3D_4H_6S_9S_4S_4C_TC_9C_5C () {
        //given
        String[] pokers = {"3S", "3D", "4H", "6S", "9S", "4S", "4C", "TC", "9C", "5C"};
        PokerGame pokerGame = new PokerGame(pokers);

        //when
        String result = pokerGame.compare();

        //then
        assertEquals("player2 win", result);
    }


}
