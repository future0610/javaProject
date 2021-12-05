package TicTacToe;

import com.sun.source.util.Trees;

import java.util.*;

public class Main {
    public static void main(String[] args){
        String e = ticTacToe.empty;
        String p = ticTacToe.player;
        String o = ticTacToe.opponent;

        String[][] original = {{o, o, p},
                                 {e, p, o},
                                 {e, e, e}};

//        node n = new node();
//        System.out.println(n.monteCarlo(original, p));

        AI ai = new AI();
        ai.setPlayer(p);
        int[] loc = ai.method(original);
        System.out.printf("%d %d\n", loc[0], loc[1]);
//
//        display disp = new display();
//

//        String winner;
//        Method User1 = new AI();
//        Method User2 = new User();
//        String[][] board = {{e, e, e},
//                {e, e, e},
//                {e, e, e}};
//        ticTacToe game = new ticTacToe(board);
//        winner = game.run(User1, User2);
//
//        System.out.println();
//        if (winner.equals(ticTacToe.DRAW)) {
//            System.out.println("무승부입니다");
//        }
//        else {
//            System.out.printf("승자: %s\n", winner);
//        }
    }
}