package testAI;

public class Main {
    public static void main(String[] args) {
        String e = ticTacToe.empty;
        String p = ticTacToe.player;
        String o = ticTacToe.opponent;

        for (int n = 1; n <= 3; n++) {
            for (int k = 1; k <= 3; k++) {
                Method ai1 = new AI(n);
                Method ai2 = new AI(k);
                int probWin = 0;
                int probLose = 0;
                int probDraw = 0;

                ticTacToe game = new ticTacToe();
                for (int i = 0; i < 100; i++) {
                    String winner = game.run(ai1, ai2);
                    if (winner.equals(ticTacToe.player)) {
                        probWin++;
                    } else if (winner.equals(ticTacToe.opponent)) {
                        probLose++;
                    } else {
                        probDraw++;
                    }

                }
                System.out.printf("깊이가 %d인 AI가 %d인 AI를 이길 확률: %d%%\n", n, k, probWin);
                System.out.printf("깊이가 %d인 AI가 %d인 AI에게 질 확률: %d%%\n", n, k, probLose);
                System.out.printf("깊이가 %d인 AI가 %d인 AI와 비길 확률: %d%%\n", n, k, probDraw);
                System.out.println();
            }
        }
    }
}