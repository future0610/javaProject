package TicTacToe.GUI;

public class User implements Method {
    private String playerPieces;
    private gameManager manager;
    public User() {

    }

    @Override
    public int[] method(String[][] board) {
        // 보드에서 플레이어의 게임말의 위치 결정

        this.manager.resetLoc();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.manager.boardBtn[i][j].addActionListener(this.manager.gameActionListener);
            }
        }
        while (this.manager.humanLoc[0] == -1 || this.manager.humanLoc[1] == -1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    this.manager.boardBtn[i][j].addActionListener(null);
                }
            }

            if (this.manager.reInitialize == 0) {
                int[] result = {4, 4};
                return result;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.manager.boardBtn[i][j].removeActionListener(this.manager.gameActionListener);
            }
        }
        System.out.println(this.toString());
        return this.manager.humanLoc;
//        return null;
        /**
         **Console창에서 실행할 경우**
         Scanner input = new Scanner(System.in);
         int[] loc = new int[2];
         loc[0] = input.nextInt();
         loc[1] = input.nextInt();
         return loc;
        */
    }

    @Override
    public void setPlayer(String player) {
        this.playerPieces = player;
    }

    @Override
    public void setManager(gameManager manager) {
        this.manager = manager;
    }
}