package demo.alex.ttt;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BoardTest {

    @Test
    public void isOverFalse() {
        Board board = new Board();
        Assertions.assertThat(board.isOver()).isFalse();
    }

}
