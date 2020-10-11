package demo.alex.ttt;

import demo.alex.exception.TTTException;

public class Board {

    private final char board[][] = new char[3][3];

    public void move(char player, int x, int y) {
        if (x > board.length || y > board.length) {
            throw new TTTException(String.format("Invalid move at field x: %x, y: %y, move is out of board!", x, y));
        }

        if (board[x][y] != 0) {
            throw new TTTException(String.format("Invalid move at field x: %x, y: %y, cell is set!", x, y));
        }

        board[x][y] = player;
    }

    public boolean isOver() {
        for (char row[] : board) {
            for (char cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }

        return true;
    }

}
