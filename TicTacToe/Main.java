package TicTacToe;

import com.sun.source.util.Trees;

import java.util.*;

public class Main {
    public static void main(String[] args){
        String e = ticTacToe.empty;
        String p = ticTacToe.player;
        String o = ticTacToe.opponent;

        String[][] original = {{p, e, e},
                                 {e, e, e},
                                 {e, e, e}};
//        ticTacToe game = new ticTacToe(original);
//        game.reset();
//        game.draw();
//        String[][] result = new String[3][3];
//        for (int i = 0; i < 3; i++) {
//            result[i] = original[i].clone();
//        }
////        for (int i = 0; i < original.length; i++) {
////            System.arraycopy(original[i], 0, result[i], 0, original[i].length);
////        }
//        result[0][1] = p;
//        System.out.printf("%s %s\n", original[0][0], result[0][0]);

        AI ai = new AI();
        ai.method(original);

//        display disp = new display();
//        System.out.println(disp.startButton.doClick());
//        if () {

//        String winner;
//        ticTacToe game = new ticTacToe(true);
//
//        Method User1 = new User();
//        Method User2 = new User();
//
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