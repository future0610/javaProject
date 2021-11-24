package TicTacToe;

public class Main {
    public static void main(String[] args){
        String e = ticTacToe.empty;
        String p = ticTacToe.player;
        String o = ticTacToe.opponent;

        String winner;
        ticTacToe game = new ticTacToe(true);

        Method User1 = new User();
        Method User2 = new User();

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