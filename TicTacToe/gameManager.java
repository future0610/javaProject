package TicTacToe;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class gameManager extends JPanel implements ActionListener {

    JButton btnArr[][] = new JButton[3][3];
    int arr[][] = new int[3][3];
    int turn = 0;

    JButton winBtn = new JButton();
    JButton reBtn = new JButton();

    Color backgroundColor = new Color(255,255,255);
    Color btnColor = new Color(255,200,200);
    Color pushedBtnColor1 = new Color(0,255,200);
    Color pushedBtnColor2 = new Color(0,200,255);
    Color optionBtnColor = new Color(255,200,200);
    Font gameBtnFont = new Font("고딕",Font.PLAIN,20);
    Font optionBtnFont = new Font("고딕",Font.BOLD,15);

    private String[][] board;
    private boolean restart = false;

    public gameManager() {

        this.setLayout(null);
        this.setBackground(backgroundColor);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                btnArr[i][j] = new JButton();
                btnArr[i][j].setBackground(btnColor); //버튼색상
                btnArr[i][j].setFont(gameBtnFont); //폰트
//                btnArr[i][j].setText(i * 3 + j + 1 + "");
                btnArr[i][j].setSize(100, 100);
                btnArr[i][j].setLocation(43 + j * 100, 30 + i * 100);
                this.add(btnArr[i][j]);
                arr[i][j] = 100;
            }
        }


        winBtn.setText("Winner?");
        winBtn.setSize(160, 40);
        winBtn.setLocation(43, 350);		// 왼쪽 상단 0,0 기준으로 지정함.
        winBtn.setBackground(optionBtnColor);	//버튼색상
        winBtn.setFont(optionBtnFont);
        winBtn.addActionListener(this);
        this.add(winBtn);

        reBtn.setText("RePlay");
        reBtn.setSize(120, 40);
        reBtn.setLocation(223, 350);
        reBtn.setBackground(optionBtnColor);	//버튼색상
        reBtn.setFont(optionBtnFont);
        reBtn.addActionListener(this);
        this.add(reBtn);
    }

    public void getBoard(String[][] board) {
        this.board = board;
    }

    public void renewal() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                btnArr[i][j].setText(this.board[i][j]);
            }
        }
    }

    public boolean isRestart() {
        return this.restart;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == reBtn) {
            this.restart = true;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
//                    btnArr[i][j].setText(i*3+j+1+"");
                    btnArr[i][j].setEnabled(true);
                    btnArr[i][j].setBackground(btnColor);
                    winBtn.setBackground(btnColor);
                    arr[i][j] = 0;
                }
            }
            winBtn.setText("Winner?");
        }
    }
}