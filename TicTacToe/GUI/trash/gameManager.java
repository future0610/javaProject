package TicTacToe.GUI;

import javax.swing.*;
import java.awt.*;

public class gameManager {
    private JFrame frame;
    private Method user1;
    private Method user2;
    private ticTacToe game;
    private JButton[][] boardButtons;

    public gameManager() {
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public int menuOpen() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);

        JLabel instruction = new JLabel("Who will you play with?", SwingConstants.CENTER);
        instruction.setBounds(50, 50, 300, 50);
        menuPanel.add(instruction);

        JButton playWithAI = new JButton("Play with AI");
        playWithAI.setBounds(100, 150, 200, 50);
        menuPanel.add(playWithAI);

        JButton playWithHuman = new JButton("Play with Human");
        playWithHuman.setBounds(100, 250, 200, 50);
        menuPanel.add(playWithHuman);

        final int[] result = {-1}; // 반환값 저장용 배열

        playWithAI.addActionListener(e -> showDifficultySelection(result));
        playWithHuman.addActionListener(e -> {
            user1 = new User();
            user2 = new User();
            compose(); // 보드 초기화 및 기권 버튼 생성
            result[0] = 1; // 사람이 선택되면 1 반환
        });

        updatePanel(menuPanel);

        // 반환값 설정 대기
        while (result[0] == -1) {
            try {
                Thread.sleep(100); // 사용자 입력 대기
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        return result[0]; // 반환값 반환
    }

    private void showDifficultySelection(final int[] result) {
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setLayout(null);

        JLabel instruction = new JLabel("Choose AI Difficulty:", SwingConstants.CENTER);
        instruction.setBounds(50, 50, 300, 50);
        difficultyPanel.add(instruction);

        JButton[] levels = new JButton[3];
        for (int i = 0; i < 3; i++) {
            int difficulty = i + 1;
            levels[i] = new JButton("Level " + difficulty);
            levels[i].setBounds(100, 150 + (i * 70), 200, 50);
            difficultyPanel.add(levels[i]);

            levels[i].addActionListener(e -> {
                user1 = new User();
                user2 = new AI(difficulty);
                compose(); // 보드 초기화 및 기권 버튼 생성
                result[0] = 1; // 난이도를 선택한 후 1 반환
            });
        }

        updatePanel(difficultyPanel);
    }

    public void compose() {
        // 보드 초기 상태와 기권 버튼 생성
        JPanel gamePanel = new JPanel(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        game = new ticTacToe();
        game.setGame(user1, user2);

        boardButtons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j] = new JButton("");
                int x = i;
                int y = j;
                boardButtons[i][j].addActionListener(e -> handleMove(x, y));
                boardPanel.add(boardButtons[i][j]);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Player " + ticTacToe.opponent + " wins by forfeit!");
            menuOpen();
        });

        gamePanel.add(boardPanel, BorderLayout.CENTER);
        gamePanel.add(quitButton, BorderLayout.SOUTH);

        updatePanel(gamePanel);
    }

    private void handleMove(int x, int y) {
        if (!boardButtons[x][y].getText().isEmpty()) {
            return; // 이미 클릭된 칸은 무시
        }

        String currentPlayer = game.getCurrentPlayer();
        boardButtons[x][y].setText(currentPlayer);

        game.action(new int[]{x, y});
        String winner = game.evaluate();

        if (winner != null) {
            String resultMessage = winner.equals(ticTacToe.DRAW) ? "It's a draw!" : "Player " + winner + " wins!";
            JOptionPane.showMessageDialog(frame, resultMessage);
            regame();
        }
    }

    public void startGame() {
        while (game.evaluate() == null) {
            try {
                Thread.sleep(100); // 게임 진행 대기
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int regame() {
        int choice = JOptionPane.showConfirmDialog(frame, "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        return (choice == JOptionPane.YES_OPTION) ? 1 : 0;
    }

    public void close() {
        frame.dispose(); // 창 닫기
    }

    private void updatePanel(JPanel newPanel) {
        frame.getContentPane().removeAll();
        frame.add(newPanel);
        frame.revalidate();
        frame.repaint();
    }
}
