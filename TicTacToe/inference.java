package TicTacToe;

public class inference {
    private ticTacToe game;
    private String player;
    private int[] result = new int[2];
    public inference(String[][] board, String player) {
        this.game = new ticTacToe(false, board);
        this.game.reset(player);
        this.player = player;
    }
    public int[] start() {
        maximizing alpha = new maximizing(this.game.getBoard(), this.player);
        alpha.call(3);
        this.result = alpha.getResult();
        return this.result;
    }
}

abstract class node {
    protected int boundary;
    protected int monteCarlo(String[][] board, String player) {
        // MonteCarlo 방식으로 점수 계산
        ticTacToe game = new ticTacToe(false, board);
        int score = 0;
        game.reset(ticTacToe.switchPlayer(player));
        for (int accuracy = 0; accuracy < 100; accuracy++) {
            String winner = game.evaluate();
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
            game.reset(ticTacToe.switchPlayer(player));
        }
        return score;
    }

    protected void setBoundary(int boundary) {
        this.boundary = boundary;
    }
}

class maximizing extends node {
    private ticTacToe game;
    private String player;
    private int nodeScore = -101;
    private int[] result;
    public maximizing(String[][] board, String player) {
        this.game = new ticTacToe(true, board);
        this.player = player;
        this.game.reset(this.player);
    }
    public void connect(int depth) {
        if (depth > 1) {
            int score;
            for (int n = 0; n < 9; n++) {
                int i = (int) (n / 3);
                int j = (int) (n % 3);
                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                    int[] loc = {i, j};
                    if (this.game.evaluate() == null) {
                        this.game.action(loc);
                        minimizing nextNode = new minimizing(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                        nextNode.setBoundary(this.nodeScore);
                        nextNode.connect(depth - 1);
                        score = nextNode.getNodeScore();
                    }
                    else {
                        score = this.monteCarlo(this.game.getBoard(), this.player);
                    }
                    if (this.nodeScore <= score) {
                        this.nodeScore = score;
                    }
                    if ((this.boundary < 101 && this.boundary > -101) && this.boundary <= score) {
                        // beta-cut
                        break;
                    }
                    this.game.reset();
                }
            }
        }

        else {
            int score;
            int empty = 0;
            for (int n = 0; n < 9; n++) {
                int i = (int) (n / 3);
                int j = (int) (n % 3);
                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                    int[] loc = {i, j};
                    if (this.game.evaluate() == null) {
                        this.game.action(loc);
                    }
                    score = this.monteCarlo(this.game.getBoard(), this.player);
//                    this.game.draw();
//                    System.out.println(score);
                    if (this.nodeScore <= score) {
                        this.nodeScore = score;
                    }
                    if ((this.boundary < 101 && this.boundary > -101) && this.boundary <= score) {
                        // beta-cut
                        break;
                    }
                    empty++;
                    this.game.reset();
                }
            }
//            System.out.println("=====================");

            if (empty == 0) {
                this.nodeScore = this.monteCarlo(this.game.getBoard(), this.player);
            }
        }
    }

    public void call(int depth) {
        if (depth > 1) {
            int score;
            for (int n = 0; n < 9; n++) {
                int i = (int) (n / 3);
                int j = (int) (n % 3);
                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                    int[] loc = {i, j};
                    this.game.action(loc);
                    minimizing nextNode = new minimizing(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                    nextNode.setBoundary(this.nodeScore); // nextNode의 boundary = this.nodeScore
                    nextNode.connect(depth - 1);
                    score = nextNode.getNodeScore();

                    this.game.draw();
                    System.out.println(score);
                    System.out.println("##################################");
                    if (this.nodeScore <= score) {
                        this.result = loc;
                        this.nodeScore = score;
                    }
                    this.game.reset();
                }
            }
        }

        else {
            int score;
            int empty = 0;
            for (int n = 0; n < 9; n++) {
                int i = (int) (n / 3);
                int j = (int) (n % 3);
                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                    int[] loc = {i, j};
                    this.game.action(loc);
                    score = this.monteCarlo(this.game.getBoard(), this.player);
                    if (this.nodeScore <= score) {
                        this.result = loc;
                        this.nodeScore = score;
                    }
                    if ((this.boundary < 101 && this.boundary > -101) && this.boundary <= score) {
                        // beta-cut
                        break;
                    }
                    empty++;
                    this.game.reset();
                }
            }
            if (empty == 0) {
                this.nodeScore = this.monteCarlo(this.game.getBoard(), this.player);
            }
        }
    }

    public int getNodeScore() {
        return nodeScore;
    }

    public int[] getResult() {
        return result;
    }
}

class minimizing extends node {
    private ticTacToe game;
    private String player;
    private int nodeScore = 101;
    public minimizing(String[][] board, String player) {
        this.game = new ticTacToe(true, board);
        this.player = player;
        this.game.reset(this.player);
    }
    public void connect(int depth) {
        if (depth > 1) {
            int score;
            for (int n = 0; n < 9; n++) {
                int i = (int) (n / 3);
                int j = (int) (n % 3);
                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                    int[] loc = {i, j};
                    if (this.game.evaluate() == null) {
                        this.game.action(loc);
                        maximizing nextNode = new maximizing(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                        nextNode.setBoundary(this.nodeScore);
                        nextNode.connect(depth - 1);
                        score = nextNode.getNodeScore();
                    }
                    else {
                        this.game.draw();
                        score = -1 * this.monteCarlo(this.game.getBoard(), this.player);
                    }
//                    System.out.println(score);
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
//                    this.game.draw();

                    if (this.nodeScore >= score) {
                        this.nodeScore = score;
                    }
                    if ((this.boundary < 101 && this.boundary > -101) && this.boundary >= score) {
                        // alpha-cut
//                        break;
                    }
                    this.game.reset();
                }
            }
        }

        else {
            int score;
            int empty = 0;
            for (int n = 0; n < 9; n++) {
                int i = (int) (n / 3);
                int j = (int) (n % 3);
                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                    int[] loc = {i, j};
                    this.game.action(loc);
                    score = -1 * this.monteCarlo(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                    if (this.nodeScore >= score) {
                        this.nodeScore = score;
                    }
                    if ((this.boundary < 101 && this.boundary > -101) && this.boundary >= score) {
                        // alpha-cut
                        break;
                    }
                    this.game.reset();
                    empty++;
                }
            }
            if (empty == 0) {
                this.nodeScore = this.monteCarlo(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
            }
        }
    }

    public int getNodeScore() {
        return nodeScore;
    }
}