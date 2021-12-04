package TicTacToe;

import java.util.Scanner;

public class User implements Method{
    private String playerPieces;
    public User() {

    }

    @Override
    public int[] method(String[][] board) {
        // 보드에서 플레이어의 게임말의 위치 결정
        Scanner input = new Scanner(System.in);
        int[] loc = new int[2];

        int x = input.nextInt();
        int y = input.nextInt();
        loc[0] = x;
        loc[1] = y;
        return loc;
    }

    @Override
    public void setPlayer(String player) {
        this.playerPieces = player;
    }
}