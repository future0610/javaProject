package TicTacToe;

import com.sun.source.util.Trees;

import java.util.*;

public class Main {
    public static void main(String[] args){
        String e = ticTacToe.empty;
        String p = ticTacToe.player;
        String o = ticTacToe.opponent;
//        gameManager disp = new gameManager();

        String winner;
        Method User1 = new AI();
        Method User2 = new User();
        String[][] board = {{e, e, e},
                            {e, o, e},
                            {p, e, o}};
        ticTacToe game = new ticTacToe(board);
        winner = game.run(User1, User2);

        System.out.println();
        if (winner.equals(ticTacToe.DRAW)) {
            System.out.println("무승부입니다");
        }
        else {
            System.out.printf("승자: %s\n", winner);
        }
    }
}