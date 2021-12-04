package TicTacToe;

import com.sun.source.util.Trees;

import java.util.*;

public class Main {
    public static void main(String[] args){
        String e = ticTacToe.empty;
        String p = ticTacToe.player;
        String o = ticTacToe.opponent;

        String[][] original = {{p, o, o},
                                 {e, e, e},
                                 {e, e, p}};

        AI ai = new AI();
        ai.setPlayer(p);
        ai.method(original);

//        display disp = new display();


//        String winner;
//        Method User1 = new User();
//        Method User2 = new User();
//
//        ticTacToe game = new ticTacToe(original);
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