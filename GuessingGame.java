import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame {
    private int numberToGuess;
    private int attempts;
    private int maxAttempts = 10;
    private int score = 0;
    private Random random;
    
    private JLabel instructionsLabel;
    private JTextField guessInput;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;
    private JButton playAgainButton;

    public GuessingGame() {
        super("Guessing Game");
        random = new Random();
        setupUI();
        startNewGame();
    }

    private void setupUI() {
        setLayout(new GridLayout(7, 1));
        
        instructionsLabel = new JLabel("Guess a number between 1 and 100");
        add(instructionsLabel);
        
        guessInput = new JTextField();
        add(guessInput);
        
        guessButton = new JButton("Guess");
        add(guessButton);
        
        feedbackLabel = new JLabel("");
        add(feedbackLabel);
        
        attemptsLabel = new JLabel("Attempts left: " + maxAttempts);
        add(attemptsLabel);
        
        scoreLabel = new JLabel("Score: " + score);
        add(scoreLabel);
        
        playAgainButton = new JButton("Play Again");
        playAgainButton.setEnabled(false);
        add(playAgainButton);

        guessButton.addActionListener(new GuessButtonListener());
        playAgainButton.addActionListener(new PlayAgainButtonListener());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setVisible(true);
    }

    private void startNewGame() {
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
        feedbackLabel.setText("");
        attemptsLabel.setText("Attempts left: " + maxAttempts);
        playAgainButton.setEnabled(false);
        guessInput.setEnabled(true);
        guessButton.setEnabled(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessInput.getText());
                if (guess < 1 || guess > 100) {
                    feedbackLabel.setText("Please enter a number between 1 and 100.");
                    return;
                }
                attempts++;
                if (guess < numberToGuess) {
                    feedbackLabel.setText("Too low!");
                } else if (guess > numberToGuess) {
                    feedbackLabel.setText("Too high!");
                } else {
                    feedbackLabel.setText("Correct! You guessed it in " + attempts + " attempts.");
                    score++;
                    scoreLabel.setText("Score: " + score);
                    guessInput.setEnabled(false);
                    guessButton.setEnabled(false);
                    playAgainButton.setEnabled(true);
                }
                if (attempts >= maxAttempts && guess != numberToGuess) {
                    feedbackLabel.setText("Sorry, you've used all attempts. The number was " + numberToGuess + ".");
                    guessInput.setEnabled(false);
                    guessButton.setEnabled(false);
                    playAgainButton.setEnabled(true);
                }
                attemptsLabel.setText("Attempts left: " + (maxAttempts - attempts));
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Invalid input. Please enter a number.");
            }
        }
    }

    private class PlayAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startNewGame();
        }
    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}
