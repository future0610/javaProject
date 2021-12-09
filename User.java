package TicTacToeConsole;

import java.util.Scanner;

public class User implements Method{
    private String playerPieces;
    public User() {

    }

    @Override
    public int[] method(String[][] board) {
        // 보드에서 플레이어의 게임말의 위치 결정

        Scanner input = new Scanner(System.in);

        int x = input.nextInt();
        int y = input.nextInt();

        int[] loc = {x, y};

        return loc;
    }

    @Override
    public void setPlayer(String player) {
        this.playerPieces = player;
    }
}