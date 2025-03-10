package kh.edu.gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypingGame extends JFrame {
    private final WordManager wordManager = new WordManager();
    private final ScoreManager scoreManager = new ScoreManager();
    private final TimerManager timerManager;

    private final JLabel wordLabel;
    private final JTextField inputField;
    private final JLabel resultLabel;
    private final JLabel scoreLabel;
    private final JLabel timeLabel;

    public TypingGame() {
        setTitle("타자 연습 게임");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        Font koreanFont = new Font("맑은 고딕", Font.BOLD, 18);

        wordLabel = new JLabel("단어가 여기에 표시됩니다", SwingConstants.CENTER);
        wordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));

        inputField = new JTextField();
        inputField.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkWord();
            }
        });

        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(koreanFont);

        scoreLabel = new JLabel("점수: 0", SwingConstants.CENTER);
        scoreLabel.setFont(koreanFont);

        timeLabel = new JLabel("남은 시간: 60초", SwingConstants.CENTER);
        timeLabel.setFont(koreanFont);

        add(wordLabel);
        add(inputField);
        add(resultLabel);
        add(scoreLabel);
        add(timeLabel);

        timerManager = new TimerManager(this::updateTime, this::endGame);
        startGame();
    }

    private void startGame() {
        nextWord();
        timerManager.start();
    }

    private void nextWord() {
        String newWord = wordManager.nextWord();
        SwingUtilities.invokeLater(() -> {
            wordLabel.setText(newWord);
            resultLabel.setText("");
            inputField.setText("");
        });
    }

    private void checkWord() {
        String typedText = inputField.getText();
        if (wordManager.checkWord(typedText)) {
            double timeTaken = wordManager.getTimeTaken();
            scoreManager.updateScore(typedText, timeTaken);

            resultLabel.setText("정답! (" + timeTaken + "초)");
            resultLabel.setForeground(Color.GREEN);
            scoreLabel.setText("점수: " + scoreManager.getScore());

            nextWord();
        } else {
            resultLabel.setText("오답! 다시 입력하세요");
            resultLabel.setForeground(Color.RED);
            inputField.setText("");
        }
    }

    private void updateTime() {
        timeLabel.setText("남은 시간: " + timerManager.getTimeLeft() + "초");
    }

    private void endGame() {
        inputField.setEnabled(false);
        JOptionPane.showMessageDialog(this, scoreManager.getHistory(), "게임 결과", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TypingGame game = new TypingGame();
            game.setVisible(true);
        });
    }
}