package TicTacToe.GUI;

public class AI implements Method {
    private String playerPieces;
    private int depth;

    public AI(int depth) {
        this.depth = depth;
    }

    @Override
    public int[] method(String[][] board) {
        /*
        최소극대화와 alpha-beta pruning을 이용한 게임탐색
        각 경우에 대한 점수는 Monte Carlo Method를 사용하였다.
        */
        inference inf = new inference(board, this.playerPieces);
        int[] loc = inf.start(this.depth);
        return loc;
    }

    @Override
    public void setPlayer(String player) {
        this.playerPieces = player;
    }

    @Override
    public void setManager(gameManager manager) {

    }
}