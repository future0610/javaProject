package TicTacToeConsole;

import TicTacToe.gameManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String e = ticTacToe.empty;
        String p = ticTacToe.player;
        String o = ticTacToe.opponent;

//        String[][] board = {{e, e, o},
//                            {o, p, e},
//                            {p, p, o}};

        Method User1 = new AI(3);
        Method ai = new AI(1);

        Method User2 = new User();

        Scanner input = new Scanner(System.in);

        ticTacToe game = new ticTacToe();
        String winner = game.run(User1, ai);

        if (!winner.equals(ticTacToe.DRAW)) {
            System.out.println("승자: %s".formatted(winner));
        }
        else {
            System.out.println("무승부입니다.");
        }
    }
}