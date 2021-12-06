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
    protected int selfIdx;
    protected int boundary;
    protected int monteCarlo(String[][] board, String player) {
        // MonteCarlo 방식으로 점수 계산
        ticTacToe game = new ticTacToe(false, board);
        int score = 0;
        game.reset(player);
        game.switchTurn();
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
            game.reset(player);
        }
        return score;
    }

    protected void setBoundary(int boundary) {
        this.boundary = boundary;
    }
    protected void setIndex(int index) {
        this.selfIdx = index;
    }
}

class maximizing extends node {
    private ticTacToe game;
    private String player;
    private int nodeScore = -100;
    private int index = 0;
    private int[] result;
    public maximizing(String[][] board, String player) {
        this.game = new ticTacToe(false, board);
        this.player = player;
        this.game.reset(this.player);
    }
    public void connect(int depth) {
        this.index = this.selfIdx;
        if (depth > 1) {
            for (int n = 0; n < 9; n++) {
                int i = (int) (n / 3);
                int j = (int) (n % 3);
                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                    int[] loc = {i, j};
                    this.game.action(loc);
                    minimizing nextNode = new minimizing(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                    nextNode.setIndex(this.index);
                    nextNode.setBoundary(this.nodeScore);
                    nextNode.connect(depth - 1);
                    if (this.nodeScore <= nextNode.getNodeScore()) {
                        this.nodeScore = nextNode.getNodeScore();
                    }
                    if (this.index > 0 && this.boundary <= nextNode.getNodeScore()) {
                        // beta-cut
                        break;
                    }
                    this.index++;
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
                    if (this.nodeScore <= score) {
                        this.nodeScore = score;
                    }
                    if (this.index > 0 && this.boundary <= score) {
                        // beta-cut
                        break;
                    }
                    this.index++;
                    empty++;
                    this.game.reset();
                }
            }
            if (empty == 0) {
                this.nodeScore = this.monteCarlo(this.game.getBoard(), this.player);
            }
        }
    }

    public void call(int depth) {
        if (depth > 1) {
            for (int n = 0; n < 9; n++) {
                int i = (int) (n / 3);
                int j = (int) (n % 3);
                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                    int[] loc = {i, j};
                    this.game.action(loc);
                    minimizing nextNode = new minimizing(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                    nextNode.setIndex(this.index);
                    nextNode.setBoundary(this.nodeScore);
                    nextNode.connect(depth - 1);
                    if (this.nodeScore <= nextNode.getNodeScore()) {
                        this.result = loc;
                        this.nodeScore = nextNode.getNodeScore();
                    }
                    this.index++;
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
                    if (this.nodeScore <= score) {
                        this.result = loc;
                        this.nodeScore = score;
                    }
                    if (this.index > 0 && this.boundary <= score) {
                        // beta-cut
                        break;
                    }
                    this.index++;
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
    private int nodeScore = 100;
    private int index = 0;
    public minimizing(String[][] board, String player) {
        this.game = new ticTacToe(false, board);
        this.player = player;
        this.game.reset(this.player);
    }
    public void connect(int depth) {
        this.index = this.selfIdx;
        if (depth > 1) {
            for (int n = 0; n < 9; n++) {
                int i = (int) (n / 3);
                int j = (int) (n % 3);
                if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                    int[] loc = {i, j};
                    this.game.action(loc);
                    maximizing nextNode = new maximizing(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                    nextNode.setIndex(this.index);
                    nextNode.setBoundary(this.nodeScore);
                    nextNode.connect(depth - 1);
                    if (this.nodeScore >= nextNode.getNodeScore()) {
                        this.nodeScore = nextNode.getNodeScore();
                    }
                    if (this.index > 0 && this.boundary >= nextNode.getNodeScore()) {
                        // alpha-cut
                        break;
                    }
                    this.index++;
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
//                    this.game.draw();
                    score = this.monteCarlo(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                    if (this.nodeScore >= score) {
                        this.nodeScore = score;
                    }
                    if (this.index > 0 && this.boundary >= score) {
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