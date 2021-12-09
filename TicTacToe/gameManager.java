package TicTacToe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class gameManager extends JPanel implements ActionListener {

    JButton btnArr[][] = new JButton[3][3];

    JButton winBtn = new JButton();
    JButton reBtn = new JButton();

    Color backgroundColor = new Color(255,255,255);
    Color btnColor = new Color(255,200,200);
    Color player1 = new Color(255,155,0);
    Color player2 = new Color(0,200,255);
    Color draw = new Color(100, 100, 100);
    Color optionBtnColor = new Color(255,200,200);
    Font gameBtnFont = new Font("고딕",Font.PLAIN,20);
    Font optionBtnFont = new Font("고딕",Font.BOLD,15);

    private String[][] board;
    private boolean restart = false;

    private JFrame frame;

    JButton ai = new JButton();
    JButton human = new JButton();
    JButton instruction = new JButton();

    Method User1;
    Method User2;
    ticTacToe game = new ticTacToe();

    int[] loc = {-1, -1};
    int target = -1;

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

    void menu() {
        this.setLayout(null);
        this.setBackground(backgroundColor);

        this.instruction.setSize(140, 100);
        this.instruction.setLocation(200, 200);
        this.instruction.setText("Welcome!");
        this.instruction.setBackground(optionBtnColor);
        this.instruction.setFont(optionBtnFont);
        this.instruction.setEnabled(false);
        this.add(this.instruction);

        this.ai.setSize(140, 35);
        this.ai.setLocation(50, 350);
        this.ai.setFont(gameBtnFont); //폰트
        this.ai.setEnabled(true);
        this.ai.setText("With AI");
        this.ai.addActionListener(this);

        this.add(this.ai);

        this.human.setSize(140, 35);
        this.human.setLocation(210, 350);
        this.human.setFont(gameBtnFont); //폰트
        this.human.setText("With Human");
        this.human.setEnabled(true);
        this.human.addActionListener(this);
        this.add(this.human);

        System.out.println(this.target);
    }

    private void setPlayer() {
//        if (this.target)
    }

    void revalidateFrame() {
        this.target = -1;
        this.frame.add(this);
        this.frame.revalidate();
    }

    void init() {
        this.setLayout(null);
        this.setBackground(backgroundColor);

        this.setPlayer();

        this.game.setGame(this.User1, this.User2);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                this.btnArr[i][j] = new JButton();
                this.btnArr[i][j].setBackground(btnColor); //버튼색상
                this.btnArr[i][j].setFont(gameBtnFont); //폰트
                this.btnArr[i][j].setSize(100, 100);
                this.btnArr[i][j].setLocation(43 + j * 100, 30 + i * 100);
                this.winBtn.addActionListener(this);
                this.add(this.btnArr[i][j]);
            }
        }


        this.winBtn.setText("Winner?");
        this.winBtn.setSize(160, 40);
        this.winBtn.setLocation(43, 350);		// 왼쪽 상단 0,0 기준으로 지정함.
        this.winBtn.setBackground(optionBtnColor);	//버튼색상
        this.winBtn.setFont(optionBtnFont);
        this.winBtn.addActionListener(this);
        this.add(this.winBtn);

        this.reBtn.setText("RePlay");
        this.reBtn.setSize(120, 40);
        this.reBtn.setLocation(223, 350);
        this.reBtn.setBackground(optionBtnColor);	//버튼색상
        this.reBtn.setFont(optionBtnFont);
        this.reBtn.setEnabled(true);
        this.reBtn.addActionListener(this);
        this.add(this.reBtn);
    }

    void evaluate(String winner) {
        if(winner.equals(ticTacToe.player)) {
            this.winBtn.setText("Player1 WIN!");
            this.winBtn.setBackground(this.player1);
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    this.btnArr[i][j].setEnabled(false);
                }
            }
        }

        else if(winner.equals(ticTacToe.opponent)) {
            this.winBtn.setText("Player2 WIN!");
            this.winBtn.setBackground(this.player2);
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    this.btnArr[i][j].setEnabled(false);
                }
            }
        }

        else {
            this.winBtn.setText("DRAW!");
            this.winBtn.setBackground(this.draw);
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    this.btnArr[i][j].setEnabled(false);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.btnArr[i][j].setText(this.game.getBoard()[i][j]);
                if (this.game.getBoard()[i][j].equals(ticTacToe.player)) {
                    this.btnArr[i][j].setBackground(this.player1);
                    this.btnArr[i][j].setEnabled(false);
                }

                if (this.game.getBoard()[i][j].equals(ticTacToe.opponent)) {
                    this.btnArr[i][j].setBackground(this.player2);
                    this.btnArr[i][j].setEnabled(false);
                }
            }
        }

        if (this.game.isHuman()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (e.getSource() == this.btnArr[i][j]) {
                        int[] loc = {i, j};
                        this.btnArr[i][j].setEnabled(false);
                    }
                }
            }
        }

        if (e.getSource() == this.reBtn) {
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    this.btnArr[i][j].setText(this.board[i][j]);
                    this.btnArr[i][j].setEnabled(true);
                    this.btnArr[i][j].setBackground(btnColor);
                    this.winBtn.setBackground(btnColor);
                }
            }
            this.winBtn.setText("Winner?");
            this.init();
        }
        this.game.run();
    }

    void end() {
        this.remove(this.instruction);
        this.remove(this.ai);
        this.remove(this.human);
        this.frame.remove(this);
        this.revalidateFrame();
    }
}
