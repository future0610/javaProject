package TicTacToe;

public class AI implements Method{
    String playerPieces;
    ticTacToe game;
    public AI() {
    }

    @Override
    public int[] method(String[][] board) {
        // MinMax와 alpha-beta pruning을 이용한 게임탐색
        this.game = new ticTacToe(false, board);
        this.game.reset();

        int[] loc = new int[2];
        return loc;
    }
}