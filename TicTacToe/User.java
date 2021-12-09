package TicTacToe;

import java.util.Scanner;

public class User implements Method{
    private String playerPieces;
    private gameManager manager;
    public User() {

    }

    @Override
    public int[] method(String[][] board) {
        // 보드에서 플레이어의 게임말의 위치 결정
/*        this.manager.reset();
        while (this.manager.loc[0] == -1 && this.manager.loc[1] == -1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    this.manager.btnArr[i][j].addActionListener(this.manager);
                }
            }
            this.manager.reBtn.addActionListener(this.manager);
            if (this.manager.isRestart()) {
                break;
            }
        }
        return this.manager.loc;*/
        Scanner input = new Scanner(System.in);

        int[] loc = new int[2];
        loc[0] = input.nextInt();
        loc[1] = input.nextInt();
        return loc;
    }

    @Override
    public void setPlayer(String player) {
        this.playerPieces = player;
    }

    @Override
    public void display(gameManager manager) {
        this.manager = manager;
    }
}