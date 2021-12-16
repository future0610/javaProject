package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameManager extends JPanel {
    Method user1;
    Method user2;
    int step = 0;
    int[] humanLoc = {-1, -1};
    ticTacToe game = new ticTacToe();

    gameMenu menu = new gameMenu();

    Color backgroundColor = new Color(255, 255, 255);
    Color boardColor = new Color(255, 255, 200);

    JFrame frame = new JFrame();
    JButton[][] boardBtn = new JButton[3][3];
    JButton reGameBtn = new JButton();
    ActionListener menuOptionListener = new ActionListener() {
        int ai = 3;
        int step = 0;
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 3; i++) {
                if (e.getSource() == gameManager.this.menu.gameLevel[i]) {
                    this.ai = i + 1;
                }
            }
            if (e.getSource() == gameManager.this.menu.select[0]) {
                gameManager.this.step++;
                if (gameManager.this.step == 1) {
                    gameManager.this.menu.instruction.setText("Choose Player2");
                    gameManager.this.menu.instruction.setBackground(gameManager.this.menu.colorP2);
                    gameManager.this.user1 = new AI(this.ai);
                }
                if (gameManager.this.step == 2) {
                    gameManager.this.user2 = new AI(this.ai);
                }
            }
            if (e.getSource() == gameManager.this.menu.select[1]) {
                gameManager.this.step++;
                if (gameManager.this.step == 1) {
                    gameManager.this.menu.instruction.setText("Choose Player2");
                    gameManager.this.menu.instruction.setBackground(gameManager.this.menu.colorP2);
                    gameManager.this.user1 = new User();
                }
                if (gameManager.this.step == 2) {
                    gameManager.this.user2 = new User();
                }
            }
        }
    };

    ActionListener gameActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (e.getSource() == gameManager.this.boardBtn[i][j]) {
                        gameManager.this.humanLoc[0] = i;
                        gameManager.this.humanLoc[1] = j;
                    }
                }
            }

            if (e.getSource() == gameManager.this.reGameBtn) {
                gameManager.this.game.restart(0);
                gameManager.this.renewal(gameManager.this.game.getBoard());
            }
        }
    };

    public gameManager() {
        JFrame frame = new JFrame("Tic Tac Toe");

        frame.setSize(400, 460);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension size = tk.getScreenSize();
        frame.setLocation((size.width - 400) / 2, (size.height - 460) / 2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        this.frame = frame;
    }

    public void init() {
        this.setLayout(null);
        this.setBackground(this.backgroundColor);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.boardBtn[i][j] = new JButton();
                this.boardBtn[i][j].setText(this.game.getBoard()[i][j]);
                this.boardBtn[i][j].setBackground(this.boardColor);
                this.boardBtn[i][j].setEnabled(true);
                this.boardBtn[i][j].setSize(100, 100);
                this.boardBtn[i][j].setLocation(43 + j * 100, 30 + i * 100);
                this.add(this.boardBtn[i][j]);
            }
        }

        this.reGameBtn.setText("restart?");
        this.reGameBtn.setEnabled(true);
        this.reGameBtn.setSize(150, 50);
        this.reGameBtn.setLocation(200 - 75, 350);
        this.reGameBtn.addActionListener(this.gameActionListener);
        this.add(this.reGameBtn);

        this.update();
    }

    void renewal(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(ticTacToe.player)) {
                    this.boardBtn[i][j].setText(board[i][j]);
                    this.boardBtn[i][j].setEnabled(false);
                }
                else if (board[i][j].equals(ticTacToe.opponent)) {
                    this.boardBtn[i][j].setText(board[i][j]);
                    this.boardBtn[i][j].setEnabled(false);
                }
                else {
                    this.boardBtn[i][j].setText(board[i][j]);
                    this.boardBtn[i][j].setEnabled(true);
                }
                this.boardBtn[i][j].setForeground(Color.black);
            }
        }
        this.update();
    }

    public int menuOpen() {
        this.frame.add(this.menu);
        this.frame.revalidate();
        for (int i = 0; i < 2; i++) {
            this.menu.select[i].addActionListener(this.menuOptionListener);
        }
        for (int i = 0; i < 3; i++) {
            this.menu.gameLevel[i].addActionListener(this.menuOptionListener);
        }
        while (this.step < 2) {
            for (int i = 0; i < 2; i++) {
                this.menu.select[i].addActionListener(null);
            }
        }
        return this.start();
    }

    public int start() {
        this.frame.remove(this.menu);
        this.update();
        this.frame.add(this);
        return this.runGame();
    }

    public int runGame() {
        int result = 0;
        this.game.setFrame(this);
        this.game.setGame(this.user1, this.user2);
        this.init();
        this.game.readyGame();
        while (result == 0) {
            String winner = null;
            while (winner == null) {
                winner = this.game.run();
            }
            if (winner.equals(ticTacToe.DRAW)) {
                JOptionPane.showMessageDialog(null, "무승부입니다!");
            } else {
                JOptionPane.showMessageDialog(null, "%s가 이겼습니다!".formatted(winner));
            }
            result = JOptionPane.showConfirmDialog(null, "다시 시작하겠습니까?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (result == 2) {
                System.exit(0);
            }
            if (result == 0) {
                this.game.restart(0);
                this.game.readyGame();
            }
        }
        return result;
    }

    void update() {
        this.frame.add(this);
        this.frame.validate();
        this.frame.repaint();
    }

    void resetLoc() {
        this.humanLoc[0] = -1;
        this.humanLoc[1] = -1;
    }

    void die() {
        this.step = 0;
        this.menu = new gameMenu();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.remove(this.boardBtn[i][j]);
            }
        }
        this.remove(this.reGameBtn);

        this.user1 = null;
        this.user2 = null;
        this.humanLoc[0] = -1;
        this.humanLoc[1] = -1;
        this.game = new ticTacToe();
    }

}

class gameMenu extends JPanel {

    JFrame frame = new JFrame();
    JButton[] select = new JButton[2];
    JLabel instruction = new JLabel();

    Color backgroundColor = new Color(255, 255, 255);
    Color colorP1 = new Color(150, 150, 255);
    Color buttonColor = new Color(255, 255, 200);
    Color colorP2 = new Color(255, 150, 150);
    JButton[] gameLevel = new JButton[3];

    public gameMenu() {
        this.setLayout(null);
        this.setBackground(this.backgroundColor);

        this.instruction.setText("Choose player1");
        this.instruction.setOpaque(true);
        this.instruction.setBackground(this.colorP1);
        this.instruction.setHorizontalAlignment(JLabel.CENTER);
        this.instruction.setSize(100, 100);
        this.instruction.setLocation(150, 150);

        this.add(this.instruction);

        for (int i = 0; i < 2; i++) {
            this.select[i] = new JButton();
            if (i == 0) {
                this.select[i].setText("With AI");
                this.select[i].setSize(150, 50);
                this.select[i].setLocation(200 - 160, 350);
            }
            else {
                this.select[i].setText("With Human");
                this.select[i].setSize(150, 50);
                this.select[i].setLocation(200 + 10, 350);
            }
            this.select[i].setBackground(this.buttonColor);

            this.add(this.select[i]);
        }
        for (int i = 0; i < 3; i++) {
            this.gameLevel[i] = new JButton();
            this.gameLevel[i].setSize(50, 50);
            this.gameLevel[i].setText("%d".formatted(i + 1));
            this.gameLevel[i].setLocation(200 - 25 * (2 - i) - 50 * (1 - i), 280);
            this.add(this.gameLevel[i]);
        }
    }
}