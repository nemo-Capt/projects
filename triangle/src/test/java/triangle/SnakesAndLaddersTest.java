package triangle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakesAndLaddersTest {

    @Test
    public void exampleTests() {
        SnakesAndLadders game = new SnakesAndLadders();
        assertEquals("Player 1 is on square 14", game.play(4, 3));
        assertEquals("Player 2 is on square 6", game.play(2, 4));
        assertEquals("Player 1 is on square 22", game.play(6, 2));
        assertEquals("Player 2 is on square 31", game.play(1, 1));
    }

}
