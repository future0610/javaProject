package TicTacToe.GUI;

public class inference {
    private ticTacToe game;
    private String player;
    private int[] result = new int[2];
    public inference(String[][] board, String player) {
        this.game = new ticTacToe(false, board);
        this.game.reset(player);
        this.player = player;
    }

    public int[] start(int depth) {
        maximizing alpha = new maximizing(this.game.getBoard(), this.player);
        alpha.call(depth);
        this.result = alpha.getResult();
        return this.result;
    }
}

interface generalNode {
    abstract void connect(int depth);
    abstract void reScore();
    abstract boolean cut();
    abstract int scoring();
    abstract int getNodeScore();
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

    public void setBoundary(int boundary) {
        this.boundary = boundary;
    }
}

class maximizing extends node implements generalNode {
    private ticTacToe game;
    private String player;
    private int nodeScore = -101;
    private int score;
    private int[] result;
    maximizing(String[][] board, String player) {
        this.game = new ticTacToe(true, board);
        this.player = player;
        this.game.reset(this.player);
    }

    void call(int depth) {
        for (int n = 0; n < 9; n++) {
            int i = (int) (n / 3);
            int j = (int) (n % 3);
            if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                int[] loc = {i, j};
                this.game.action(loc);
                if (depth > 1) {
                    minimizing nextNode = new minimizing(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                    nextNode.setBoundary(this.nodeScore); // nextNode의 boundary = this.nodeScore
                    nextNode.connect(depth - 1);
                    this.score = nextNode.getNodeScore();
                }
                else{
                    this.score = this.scoring();
                }
                this.reScore(loc);
                /**
                **점수 확인**
                System.out.println();
                System.out.printf("maximizing node lvl3 score: %d", this.score);
                System.out.println();
                */
                this.game.reset();
            }
        }
        /**
        **점수 확인**
        System.out.println();
        System.out.printf("maximizing node lvl3 score: %d", this.nodeScore);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println();
         */
    }

    int[] getResult() {
        return result;
    }

    public void reScore(int[] loc) {
        if (this.nodeScore < this.score) {
            this.result = loc;
            this.nodeScore = this.score;
        }
    }

    @Override
    public void connect(int depth) {
        int empty = 0;
        for (int n = 0; n < 9; n++) {
            int i = (int) (n / 3);
            int j = (int) (n % 3);
            if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                empty++;
                int[] loc = {i, j};
                if (depth > 1) {
                    if (this.game.evaluate() == null) {
                        this.game.action(loc);
                        minimizing nextNode = new minimizing(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                        nextNode.setBoundary(this.nodeScore);
                        nextNode.connect(depth - 1);
                        this.score = nextNode.getNodeScore();
                    }
                    else {
                        this.score = this.scoring();
                    }
                }
                else {
                    if (this.game.evaluate() == null) {
                        this.game.action(loc);
                    }
                    this.score = this.scoring();
                    /**
                    **점수 확인**
                    System.out.println("maximizing node lvl1 score: %d".formatted(this.score));
                     */
                }
                this.reScore();
                if (this.cut()) {
                    // beta-cut
                    break;
                }
                this.game.reset();
            }
        }
        if (empty == 0) {
            this.nodeScore = this.scoring();
        }
        /**
        **점수 확인**
        if (depth == 1) {
            System.out.printf("maximizing node lvl1 maximum: %d", this.nodeScore);
            System.out.println("=====================");
        }
        else {
            System.out.printf("maximizing node lvl3 maximum: %d", this.nodeScore);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
        }
        */
    }

    @Override
    public int scoring() {
        return this.monteCarlo(this.game.getBoard(), this.player);
    }

    @Override
    public boolean cut() {
        if ((this.boundary < 101 && this.boundary > -101) && this.boundary <= this.score){
            return true;
        }
        return false;
    }

    @Override
    public void reScore() {
        if (this.nodeScore < this.score) {
            this.nodeScore = this.score;
        }
    }

    @Override
    public int getNodeScore() {
        return nodeScore;
    }
}

class minimizing extends node implements generalNode {
    private ticTacToe game;
    private String player;
    private int nodeScore = 101;
    private int score;

    minimizing(String[][] board, String player) {
        this.game = new ticTacToe(false, board);
        this.player = player;
        this.game.reset(this.player);
    }

    @Override
    public void connect(int depth) {
        int empty = 0;
        for (int n = 0; n < 9; n++) {
            int i = (int) (n / 3);
            int j = (int) (n % 3);
            if (this.game.getBoard()[i][j].equals(ticTacToe.empty)) {
                empty++;
                int[] loc = {i, j};
                if (depth > 1) {
                    if (this.game.evaluate() == null) {
                        this.game.action(loc);
                        maximizing nextNode = new maximizing(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
                        nextNode.setBoundary(this.nodeScore);
                        nextNode.connect(depth - 1);
                        this.score = nextNode.getNodeScore();
                    } else {
                        this.score = this.scoring();
                    }

                } else {
                    if (this.game.evaluate() == null) {
                        this.game.action(loc);
                    }
                    this.score = this.scoring();
                }

                this.reScore();
                /**
                **점수 확인**
                if (depth > 1) {
                    System.out.println();
                    System.out.printf("minimizing node lvl2 score: %d\n", this.score);
                    System.out.println();
                }
                */
                if (this.cut()) {
                    // alpha-cut
                    break;
                }
                this.game.reset();
            }
        }
        if (empty == 0) {
            this.nodeScore = this.scoring();
        }
        /**
        **점수 확인**
        if (depth > 1) {
            System.out.println();
            System.out.printf("minimizing node lvl2 score: %d", this.nodeScore);
            System.out.println("-------------------------");
            System.out.println();
        }
        */
    }

    @Override
    public boolean cut() {
        if ((this.boundary < 101 && this.boundary > -101) && this.boundary >= score) {
            return true;
        }
        return false;
    }

    @Override
    public void reScore() {
        if (this.nodeScore > this.score) {
            this.nodeScore = this.score;
        }
    }

    @Override
    public int scoring() {
        return this.monteCarlo(this.game.getBoard(), ticTacToe.switchPlayer(this.player));
    }

    @Override
    public int getNodeScore() {
        return nodeScore;
    }
}