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

class MyPanel extends JPanel implements ActionListener {

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

    MyPanel() {

        this.setLayout(null);
        this.setBackground(backgroundColor);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                btnArr[i][j] = new JButton();
                btnArr[i][j].setBackground(btnColor); //버튼색상
                btnArr[i][j].setFont(gameBtnFont); //폰트
                btnArr[i][j].setText(i * 3 + j + 1 + "");
                btnArr[i][j].setSize(100, 100);
                btnArr[i][j].setLocation(43 + j * 100, 30 + i * 100);
                btnArr[i][j].addActionListener(this);
                this.add(btnArr[i][j]);
                arr[i][j] = 0;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(e.getSource() == btnArr[i][j]) {
                    if(turn % 2 == 0) {
                        btnArr[i][j].setText("O");
                        btnArr[i][j].setBackground(pushedBtnColor1);
                        arr[i][j] = 1;
                    }
                    else {
                        btnArr[i][j].setText("X");
                        btnArr[i][j].setBackground(pushedBtnColor2);
                        arr[i][j] = 2;
                    }
                    btnArr[i][j].setEnabled(false);
                    turn++;
                }
            }
        }

        if(e.getSource() == reBtn) {
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    btnArr[i][j].setText(i*3+j+1+"");
                    btnArr[i][j].setEnabled(true);
                    btnArr[i][j].setBackground(btnColor);
                    winBtn.setBackground(btnColor);
                    arr[i][j] = 0;
                }
            }
            winBtn.setText("Winner?");
            turn = 0;
            System.out.println(btnArr[0][0].getLocation().x);
        }

//        if(check() == 1) {
//            winBtn.setText("Player1 WIN!");
//            winBtn.setBackground(pushedBtnColor1);
//            for(int i=0; i<3; i++) {
//                for(int j=0; j<3; j++) {
//                    btnArr[i][j].setEnabled(false);
//                }
//            }
//        }
//        if(check() == 2) {
//            winBtn.setText("Player2 WIN!");
//            winBtn.setBackground(pushedBtnColor2);
//            for(int i=0; i<3; i++) {
//                for(int j=0; j<3; j++) {
//                    btnArr[i][j].setEnabled(false);
//                }
//            }
//        }
    }
}

public class display {

    public display() {

        JFrame frame = new JFrame("Tic Tac Toe");

        frame.setSize(400, 460);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension size = tk.getScreenSize();
        frame.setLocation((size.width-400)/2, (size.height-460)/2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

//		frame.setContentPane(new MyPanel());
        frame.add(new MyPanel());
//		MyPanel mp = new MyPanel();
//		frame.setContentPane(mp);

        frame.revalidate();
    }
}
