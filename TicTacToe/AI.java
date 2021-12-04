package TicTacToe;

public class AI implements Method{
    private String playerPieces;
    private ticTacToe game;
//    private node infMethod;

    public AI() {
    }

    @Override
    public int[] method(String[][] board) {
        // 최소극대화와 alpha-beta pruning을 이용한 게임탐색
//        infMethod = new node(board, this.playerPieces);
//        infMethod.simulate();
//        infMethod.search();
        inference inf = new inference(board, this.playerPieces);
        inf.start();
        int[] loc = new int[2];
        return loc;
    }

    @Override
    public void setPlayer(String player) {
        this.playerPieces = player;
    }
}


//class node extends inference{
//    int bound = 0;
//    int depth = 3;
//    int[] result;
//    String player;
//    ticTacToe game;
//
//    public node(String[][] board, String player) {
//        this.game = new ticTacToe(true, board);
//        this.player = player;
//        this.game.reset(player);
//    }
//
//    public int[] simulate() {
//
//        return result;
//    }
//
//    @Override
//    public void search() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                int[] loc = {i, j};
//                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
//                    this.game.action(loc);
//                    this.game.draw();
//                    inference nextNode = new nodeDeeper(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
//                    nextNode.search();
//                }
//                this.game.reset();
//            }
//        }
//    }
//}
//
//class nodeDeeper extends inference {
//    int bound = 0;
//    int depth = 2;
//    int[] result;
//    ticTacToe game;
//
//    public nodeDeeper(String[][] board, String player) {
//        this.game = new ticTacToe(true, board);
//        this.game.reset(player);
//    }
//
//    @Override
//    public void search() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                int[] loc = {i, j};
//                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
//                    this.game.action(loc);
//                    this.game.draw();
//                }
//                this.game.reset();
//            }
//        }
//    }
//}
//
//class nodeDeepest extends inference {
//    int bound = 0;
//    int depth = 1;
//    int[] result;
//    ticTacToe game;
//
//    public nodeDeepest(String[][] board, String player) {
//        this.game = new ticTacToe(true, board);
//        this.game.reset(player);
//    }
//
//    public int[] simulate() {
//
//        return result;
//    }
//
//    @Override
//    public void search() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                int[] loc = {i, j};
//                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
//                    this.game.action(loc);
//                    this.game.draw();
//                }
//                this.game.reset();
//            }
//        }
//    }
//}