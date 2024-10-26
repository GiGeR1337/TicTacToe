import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Main extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[5][5];

    static {
        System.loadLibrary("libTicTacToe");
    }

    public native void resetGame();
    public native char[] getBoard();
    public native int makeMove(int row, int col);
    public native char getCurrentPlayer();
    public native int moveActiveCell(int direction);

    public Main() {
        setTitle("5x5 Tic-Tac-Toe");
        setSize(500, 500);
        setLayout(new GridLayout(5, 5));

        initializeButtons();
        resetGame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeButtons() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
    }

    private void updateBoard() {
        char[] board = getBoard();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                buttons[row][col].setText(String.valueOf(board[row * 5 + col]));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        int row = -1, col = -1;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (buttons[i][j] == clickedButton) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        int result = makeMove(row, col);

        updateBoard();

        if (result == 1) {
            JOptionPane.showMessageDialog(this, "Player " + getCurrentPlayer() + " wins!");
            resetGame();
        } else if (result == 2) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            resetGame();
        }
    }
    public void placeMove(){}

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();

        switch (keyCode){
            case KeyEvent.VK_UP:
                moveActiveCell(0);
                break;
            case KeyEvent.VK_DOWN:
                moveActiveCell(1);
                break;
            case KeyEvent.VK_LEFT:
                moveActiveCell(2);
                break;
            case KeyEvent.VK_RIGHT:
                moveActiveCell(3);
                break;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
                placeMove();
                break;
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}