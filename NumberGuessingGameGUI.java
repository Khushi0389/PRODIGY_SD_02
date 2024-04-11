import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private int generatedNumber;
    private int attempts;
    private JTextField guessField;
    private JLabel feedbackLabel;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel guessLabel = new JLabel("Enter your guess (between 1 and 100): ");
        guessField = new JTextField(10);
        topPanel.add(guessLabel);
        topPanel.add(guessField);

        JPanel centerPanel = new JPanel(new FlowLayout());
        JButton guessButton = new JButton("Guess");
        feedbackLabel = new JLabel();
        centerPanel.add(guessButton);
        centerPanel.add(feedbackLabel);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        initializeGame();
    }

    private void initializeGame() {
        Random random = new Random();
        generatedNumber = random.nextInt(100) + 1; // Generates a random number between 1 and 100
        attempts = 0;
        feedbackLabel.setText("");
        guessField.setText("");
        guessField.requestFocus();
    }

    private void checkGuess() {
        String guessText = guessField.getText();
        try {
            int guess = Integer.parseInt(guessText);
            attempts++;

            if (guess == generatedNumber) {
                JOptionPane.showMessageDialog(this,
                        "Congratulations! You guessed the number correctly!\nNumber of attempts: " + attempts,
                        "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
                initializeGame();
            } else if (guess < generatedNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else {
                feedbackLabel.setText("Too high! Try again.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGameGUI().setVisible(true);
            }
        });
    }
}
