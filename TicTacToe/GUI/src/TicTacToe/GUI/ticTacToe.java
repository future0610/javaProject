package TicTacToe.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ticTacToe {
    private static boolean drawBoardDefault = true;
    static String player = "X";
    static String opponent = "O";
    static String empty = " ";
    static String DRAW = "draw";
    private static String[][] boardDefault = null;

    private boolean drawBoard;
    private String[][] board = null;
    String[][] initialBoard;

    private Method player1;
    private Method player2;
    private Method currentPlayer;
    private String currentPieces;
    String winner = null;

    private gameManager manager;

    public ticTacToe() {
        this(drawBoardDefault, boardDefault);
    }
    public ticTacToe(boolean draw) {
        this(draw, boardDefault);
    }
    public ticTacToe(String[][] board) {
        this(drawBoardDefault, board);
    }
    public ticTacToe(boolean draw, String[][] board) {
        this.drawBoard = draw;
        /*
        게임 보드를 화면에 표시할 것인지 나타내는 draw(default:true), 현재 보드의 상태인 board를 입력받는다.
        board로 null을 받으면 모든 칸이 비어있는 보드를 초기 보드 상태로 설정한다.
        String[][] board의 원소로서 X, O가 아닌 값이 있거나,
        O 또는 X의 수가 X 또는 O의 수의 차이가 2 이상일 때 예외를 발생시켜 프로그램을 종효시킨다.
        */
        String[][] boardState = {{empty, empty, empty},
                {empty, empty, empty},
                {empty, empty, empty}};

        if (board == null) {
            this.initialBoard = boardState;
        }
        else {
            try {
                int disable = 0;
                int numPlayer = 0;
                int numOpponent = 0;
                for (String[] col : board) {
                    for (String item : col) {
                        if (!item.equals(opponent) && !item.equals(player) && !item.equals(empty)) {
                            disable++;
                        } else if (item.equals(opponent)) {
                            numOpponent++;
                        } else if (item.equals(player)) {
                            numPlayer++;
                        }
                    }
                }
                if (disable == 0) {
                    this.initialBoard = ticTacToe.copyBoard(board);
                } else {
                    throw new Exception("X, O가 아닌 값이 포함되어있습니다.");
                }
                if ((numOpponent - numPlayer) * (numOpponent - numPlayer) < 4) {
                    this.initialBoard = ticTacToe.copyBoard(board);
                } else {
                    throw new Exception("가능하지 않은 초기 보드 상태입니다.");
                }
            } catch (Exception e) {
                System.err.println(e);
                System.exit(1);
            } finally {
            }
        }
        if (!draw) {
            this.drawBoard = false;
        }
    }

    /**
    승패 여부 확인
    */

    public String evaluate(){
        List<String> playerList = new ArrayList<String>();
        playerList.add(ticTacToe.player);
        playerList.add(ticTacToe.opponent);
        for (String players : playerList) {
            for (List<List> set : this.winCase()) {
                int winning = 0;
                for (List<Integer> loc : set) {
                    if (this.board[loc.get(0)][loc.get(1)].equals(players)) {
                        winning++;
                    }
                }
                if (winning == 3) {
                    return players;
                }
            }
        }
        int winning = 0;
        for (String[] col : this.board) {
            for (String item : col) {
                if (item.equals(this.empty)) {
                    winning++;
                }
            }
        }
        if (winning == 0) {
            return ticTacToe.DRAW;
        }

        return null;
    }

    private List<List> winCase() {
        /*
        이기는 모든 경우의 수를 구하는 메서드
        3차원 텐서를 반환하고,
        반환값의 각 요소는 2차원 좌표를 포함한 집합이다.
        */

        List<List> winSet = new ArrayList<List>();
        List<List> set = new ArrayList<List>();
        List<Integer> loc = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) {
            loc.add(i);
            loc.add(i);
            set.add(loc);

            loc = new ArrayList<Integer>();
        }
        winSet.add(set);
        set = new ArrayList<List>();

        for (int i = 0; i < 3; i++) {
            loc.add(i);
            loc.add(2 - i);
            set.add(loc);

            loc = new ArrayList<Integer>();
        }
        winSet.add(set);
        set = new ArrayList<List>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                loc.add(i);
                loc.add(j);
                set.add(loc);

                loc = new ArrayList<Integer>();
            }
            winSet.add(set);
            set = new ArrayList<List>();

            for (int j = 0; j < 3; j++) {
                loc.add(j);
                loc.add(i);
                set.add(loc);

                loc = new ArrayList<Integer>();
            }
            winSet.add(set);
            set = new ArrayList<List>();
        }
        return winSet;
    }

    /**
    게임 진행 관련
    */


    void action(int[] loc) {
        /*
        플레이어가 정한 위치에 사용자의 게임말을 놓아주는 메서드
        */
        int x = loc[0];
        int y = loc[1];
        if (this.board[x][y].equals(ticTacToe.empty)) {
            this.board[x][y] = this.currentPieces;
        }
        else {
            /*
            **Console창에서 실행할 경우**
            System.out.println("비어 있지 않은 자리입니다.");
            */
            this.switchTurn();
        }
    }


    void setGame(Method user1, Method user2) {
        /*
        플레이어 설정하는 메서드
        */
        this.player1 = user1;
        this.player2 = user2;
        this.reset();
    }

    void readyGame() {
        /*
        화면에 초기 보드가 보여지도록 하는 메서드
        */
        this.manager.renewal(this.board);
    }

    /**
     * 게임을 진행하는 메서드
     */

    String run(){
        int[] loc;

        this.currentPlayer.setManager(this.manager);
        this.currentPlayer.setPlayer(this.currentPieces);
        System.out.println(this.currentPlayer);
        loc = this.currentPlayer.method(this.board);
        System.out.print(loc[0]);
        System.out.println(loc[1]);
        if (loc[0] == 4 || loc[1] == 4) {
            return this.winner;
        }
        this.action(loc);
        System.out.println(this.currentPlayer);
        System.out.println();
//        System.out.println(this.currentPieces);
        this.switchTurn();
        this.manager.renewal(this.board);
        this.winner = this.evaluate();
        return this.winner;
        /*
        **Console창에서 실행할 경우**
        System.out.printf("현재 플레이어: %s\n", this.currentPieces);
        this.draw();
        System.out.print("위치를 입력하세요: ");
        while (winner == null){
            System.out.printf("현재 플레이어: %s\n", this.currentPieces);
            this.draw();
            int[] loc;
            System.out.print("위치를 입력하세요: ");

            this.currentPlayer.setPlayer(this.currentPieces);
            loc = this.currentPlayer.method(this.board);
            if (loc[0] != -1 && loc[1] != -1) {
                this.action(loc);
                switchTurn();
            }
            else {
                winner = this.restart(winner);
            }

            winner = this.evaluate();
            System.out.println();
        }
        this.draw();
        */
    }


    void restart(int step) {
        /*
        게임을 재시작하는 메서드
        */
        this.currentPieces = null;
        this.currentPlayer = null;
        if (step == 0) {
            this.reset();
        }
    }

    /**
    GUI 관련
    */

    public void setFrame(gameManager manager) {
        /*
        게임을 화면에 나타내어 줄 gameManager 설정
        */
        this.manager = manager;
    }


    public void draw() {
        // 게임판을 그려주는 메서드
        if (this.drawBoard) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print("---|");
            }
            System.out.println();
            for (int i = 0; i < 3; i++) {
                System.out.print("|");
                for (int j = 0; j < 3; j++) {
                    System.out.printf(" %s ", this.board[i][j]);
                    System.out.print("|");
                }
                System.out.println();
                System.out.print("|");
                for (int j = 0; j < 3; j++) {
                    System.out.print("---|");
                }
                System.out.println();
            }
        }
    }

    /**
    게임 순서를 정할 때 사용하는 메서드
    */

    public void reset(String player) {
        /*
        게임판을 초기화시키는 메서드
        현재 플레이어를 player로 고정한다
        */
        this.board = ticTacToe.copyBoard(this.initialBoard);
        this.currentPieces = player;
        if (player.equals(ticTacToe.player)) {
            this.currentPlayer = this.player1;
        }
        else if (player.equals(ticTacToe.player)) {
            this.currentPlayer = this.player2;
        }
    }

    public void reset() {
        /*
         게임판과 순서를 초기화시키는 메서드
         */
        try {
            System.out.println(this.currentPlayer);
        }
        catch (Exception e) {

        }
        this.board = ticTacToe.copyBoard(this.initialBoard);
        if (this.currentPieces == null) {
            this.currentPieces = this.nextPlayer();
            System.out.println();
            System.out.print("next:");
            System.out.println(this.currentPlayer);
        }
    }

    private String nextPlayer(){
        /*
        게임 상태에 따라 순서를 정해주는 메서드
        1. 게임판에 O와 X의 수가 같으면 50%의 확률로 순서가 정해짐
        2. 게임판에서 수가 적은 쪽이 선공
        */
        int numPlayer = 0;
        int numOpponent = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j].equals(ticTacToe.player)) {
                    numPlayer++;
                }
                else if (this.board[i][j].equals(ticTacToe.opponent)){
                    numOpponent++;
                }
            }
        }
        if (numOpponent == numPlayer) {
            Random random = new Random();
            int prob = random.nextInt(2);
            return this.randomTurn(prob);
        }
        else if (numOpponent > numPlayer){
            this.currentPlayer = this.player1;
            return ticTacToe.player;
        }
        else {
            this.currentPlayer = this.player2;
            return ticTacToe.opponent;
        }
    }

    /**
     * 순서를 바꾸는 메서드
     */
    public void switchTurn() {
        if (this.currentPieces.equals(ticTacToe.opponent)){
            this.currentPieces = ticTacToe.player;
            this.currentPlayer = this.player1;
        }
        else {
            this.currentPieces = ticTacToe.opponent;
            this.currentPlayer = this.player2;
        }
    }
    private String randomTurn(int prob) {
        if (prob == 0) {
            this.currentPlayer = this.player1;
            return ticTacToe.player;
        }
        else {
            this.currentPlayer = this.player2;
            return ticTacToe.opponent;
        }
    }

    /**
    내장 함수
    */

    public static int[] randomAction() {
        /*
        말의 위치를 무작위로 정하는 메서드
        */
        Random random = new Random();
        int x, y;
        int[] loc = new int[2];

        x = random.nextInt(3);
        y = random.nextInt(3);
        loc[0] = x;
        loc[1] = y;

        return loc;
    }

    public String[][] getBoard() {
        return this.board;
    }

    public static String[][] copyBoard(String[][] board) {
        String[][] result = new String[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, result[i], 0, board[i].length);
        }
        return result;
    }

    public static String switchPlayer(String player) {
        String currentPlayer = ticTacToe.player;
        if (player.equals(ticTacToe.player)) {
            currentPlayer = ticTacToe.opponent;
        }

        return currentPlayer;
    }
}