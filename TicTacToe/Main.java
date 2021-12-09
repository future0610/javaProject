package TicTacToe;

public class Main {
    public static void main(String[] args){
        String e = ticTacToe.empty;
        String p = ticTacToe.player;
        String o = ticTacToe.opponent;

        Method User1 = null;
        Method User2 = null;

        gameManager manager = new gameManager();
        manager.menu();
        manager.revalidateFrame();
        User1 = manager.User1;
        User2 = manager.User2;

//        Method User1 = new AI();
//        Method User2 = new User();
//        System.out.println("here");
//        manager.revalidateFrame();
//        while (User1 == null) {
//            User1 = manager.choosePlayer();
//            manager.revalidateFrame();
//        }
//
//        while (User2 == null) {
//            User2 = manager.choosePlayer();
//            manager.revalidateFrame();
//        }
        System.out.println("hi");
//        manager.remove(manager);
//        manager.revalidateFrame();


//        String[][] board = {{e, e, o},
//                            {o, p, e},
//                            {p, p, o}};

//        Method User1 = new AI();
//        Method User2 = new User();
//        gameManager manager = new gameManager();

//        ticTacToe game = new ticTacToe();
//        game.setFrame(manager);
//        game.run(User1, User2);
    }
}