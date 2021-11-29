package TicTacToe;

public class AI implements Method{
    String playerPieces;
    ticTacToe game;
    inference infMethod;
    public AI() {
    }

    @Override
    public int[] method(String[][] board) {
        // MinMax와 alpha-beta pruning을 이용한 게임탐색
        infMethod = new inference(board);
        infMethod.simulate(3);
        int[] loc = new int[2];
        return loc;
    }
}

class inference{
    int limit = 0;
    int[] result;
    ticTacToe game;
    node sim;

    public inference(String[][] board) {
        this.game = new ticTacToe(true, board);
        this.game.reset();
    }
    public int[] simulate(int depth) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] loc = {i, j};
                if (ticTacToe.empty.equals(this.game.getBoard()[i][j])) {
                    this.game.action(loc);
                    this.game.draw();
                    this.game.reset();
                    System.out.println();
                }
            }
        }
        return result;
    }
    public void monteCarlo(String[][] board) {
        String winner = null;
        while (winner == null){
            ticTacToe gM = new ticTacToe(false, board);
            int[] loc = ticTacToe.randomAction();
            gM.reset();
            gM.action(loc);
        }
    }
}

class node {
    public node() {
    }
}