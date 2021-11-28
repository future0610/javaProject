package TicTacToe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class display extends JFrame implements ActionListener {
    boolean startButton;
    JButton endButton;

    public void display() {

        // 프레임 생성
        JFrame frm = new JFrame("틱택토 게임하기");

        // 프레임 크기 설정
        frm.setSize(350, 300);

        // 프레임을 화면 가운데에 배치
        frm.setLocationRelativeTo(null);

        // 프레임을 닫았을 때 메모리에서 제거되도록 설정
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 레이아웃 설정
        frm.getContentPane().setLayout(null);

        // 버튼 생성
        JButton btn1 = new JButton("슬라삐");
        JButton btn2 = new JButton("슬라디");

        btn1.addActionListener(this);

        // ★ 버튼 위치와 크기 설정
        btn1.setBounds(30, 170, 122, 30);
        btn2.setBounds(182, 170, 122, 30);

        // ★ 프레임에다가 버튼 추가
        frm.getContentPane().add(btn1);
        frm.getContentPane().add(btn2);

        // 프레임이 보이도록 설정
        frm.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
