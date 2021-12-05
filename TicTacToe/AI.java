package TicTacToe;

public class AI implements Method{
    private String playerPieces;
    private ticTacToe game;

    public AI() {
    }

    @Override
    public int[] method(String[][] board) {
        // 최소극대화와 alpha-beta pruning을 이용한 게임탐색
//        infMethod = new node(board, this.playerPieces);
//        infMethod.simulate();
//        infMethod.search();
        inference inf = new inference(board, this.playerPieces);
        int[] loc = inf.start();
        return loc;
    }

    @Override
    public void setPlayer(String player) {
        this.playerPieces = player;
    }
}