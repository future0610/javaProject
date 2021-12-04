package TicTacToe;

// draw 인자는 현재 노드의 상태를 보기 위해 임시로 true로 한다.

public class inference {
    private int boundary;
    private ticTacToe game;
    private String player;
    private int[] result = new int[2];
    public inference(String[][] board, String player) {
        this.game = new ticTacToe(false, board);
        this.game.reset(player);
        this.player = player;
    }
    public void start() {
        alphaNode alpha = new alphaNode(this.game.getBoard(), this.player);
        alpha.connect(1);
    }

    public static int monteCarlo(String[][] board, String player) {
        // MonteCarlo 방식으로 점수 계산

        ticTacToe game = new ticTacToe(false, board);
        int score = 0;
        game.reset(player);
        for (int accuracy = 0; accuracy < 100; accuracy++) {
            String winner = null;
            while (winner == null) {
                int[] loc = ticTacToe.randomAction();
                if (game.getBoard()[loc[0]][loc[1]].equals(ticTacToe.empty)) {
                    game.action(loc);
                    winner = game.evaluate();
                    game.switchTurn();
                }
            }
            if (winner.equals(player)) {
                score += 1;
            }
            else {
                if (!winner.equals(ticTacToe.DRAW)) {
                    score -= 1;
                }
            }
            game.reset();
        }
        game.finalize();
        return score;
    }
}

interface node {

}

class alphaNode implements node{
    private ticTacToe game;
    private String player;
    private int alpha;
    private int[] result = new int[2];
    public alphaNode(String[][] board, String player) {
        this.game = new ticTacToe(false, board);
        this.game.reset(player);
        this.player = player;
    }
    public void connect(int depth) {
        int score = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                    int[] loc = {i, j};
                    this.game.action(loc);
                    if (depth > 1) {
                        betaNode beta = new betaNode(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                        beta.connect(depth - 1);
                    } else {
                        score = inference.monteCarlo(this.game.getBoard(), this.player);
                    }
                    this.game.reset();
                }
            }
        }
    }
}

class betaNode implements node {
    private ticTacToe game;
    private String player;
    public betaNode(String[][] board, String player) {
        this.game = new ticTacToe(true, board);
        this.game.reset(player);
        this.player = player;
    }

    public void connect(int depth) {
        int score = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                    int[] loc = {i, j};
                    this.game.action(loc);
                    if (depth > 1) {
                        alphaNode alpha = new alphaNode(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                        alpha.connect(depth - 1);
                    }
                    else {
                        score = inference.monteCarlo(this.game.getBoard(), this.player);
                    }
                    this.game.reset();
                }
                score = 0;

            }
        }
    }
}