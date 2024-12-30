package TicTacToe.GUI;

public class Main {
    public static void main(String[] args){
        int result;
        gameManager manager = new gameManager();
        do {
            result = manager.menuOpen();
            manager.die();

        } while(result == 1);
    }
}
