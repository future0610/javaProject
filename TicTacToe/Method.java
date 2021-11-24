package TicTacToe;

public interface Method {
    /*
    게임을 플레이하는 플레이어-상대의 조합으로
    AI, 사람에 관계없이 게임이 진행되도록 하기 위한 인터페이스
    */
    abstract int[] method(String[][] board);
}
