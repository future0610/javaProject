package TicTacToe.Console;

public class Main {
    public static void main(String[] args) {
        String e = ticTacToe.empty;
        String p = ticTacToe.player;
        String o = ticTacToe.opponent;

        ticTacToe game = new ticTacToe();

        Method user1 = new AI(3);
        Method user2 = new User();

        String winner = game.run(user1, user2);

        if (winner.equals(ticTacToe.DRAW)) {
            System.out.println("무승부입니다!");
        }
        else {
            System.out.println("승자: %s".formatted(winner));
        }


    }
}