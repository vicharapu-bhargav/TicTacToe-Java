import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI implements ActionListener {

    JFrame frame;
    JButton[] buttons = new JButton[9];
    JLabel scoreLabel;
    JButton resetScoreButton;

    boolean xTurn = true;
    int xScore = 0;
    int oScore = 0;

    public TicTacToeGUI() {
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // Top panel for score
        JPanel topPanel = new JPanel();
        scoreLabel = new JLabel("Player X: 0   |   Player O: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.setLayout(new FlowLayout());
        
        //reset button
        resetScoreButton = new JButton("Reset Score");
        resetScoreButton.addActionListener(e -> {
            xScore = 0;
            oScore = 0;
            updateScore();
        });

        topPanel.add(scoreLabel);
        topPanel.add(resetScoreButton);

        // Center panel for buttons
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            gamePanel.add(buttons[i]);
        }

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
   


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return; // Already clicked
        }

        clicked.setText(xTurn ? "X" : "O");
        clicked.setForeground(xTurn ? Color.BLUE : Color.RED);

        if (checkWinner()) {

            if (xTurn) xScore++;
            else oScore++;

            updateScore();
            JOptionPane.showMessageDialog(frame, "Player " + (xTurn ? "X" : "O") + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            resetBoard();
        } else {
            xTurn = !xTurn; // Switch turn
        }

    }

    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) return false;
        }
        return true;
    }

    private boolean checkWinner() {
        String[][] combos = {
            {text(0), text(1), text(2)},
            {text(3), text(4), text(5)},
            {text(6), text(7), text(8)},
            {text(0), text(3), text(6)},
            {text(1), text(4), text(7)},
            {text(2), text(5), text(8)},
            {text(0), text(4), text(8)},
            {text(2), text(4), text(6)}
        };

        for (String[] line : combos) {
            if (line[0].equals(line[1]) && line[1].equals(line[2]) && !line[0].equals("")) {
                return true;
            }
        }
        return false;
    }

    private void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");
            button.setForeground(Color.BLACK);
        }
        xTurn = true;
    }

    private String text(int index) {
        return buttons[index].getText();
    }

    private void updateScore() {
        scoreLabel.setText("Player X: " + xScore + "   |   Player O: " + oScore);
    }
   
}
