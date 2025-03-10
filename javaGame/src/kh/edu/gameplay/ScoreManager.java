package kh.edu.gameplay;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
    private int score = 0;
    private int correctWordCount = 0;
    private final List<String> wordHistory = new ArrayList<>();
    private final List<Double> timeHistory = new ArrayList<>();

    public void updateScore(String word, double timeTaken) {
        score++;
        correctWordCount++;
        wordHistory.add(word);
        timeHistory.add(timeTaken);
    }

    public int getScore() {
        return score;
    }

    public int getCorrectWordCount() {
        return correctWordCount;
    }

    public String getHistory() {
        StringBuilder history = new StringBuilder();
        history.append("게임 종료!\n최종 점수: ").append(score).append("\n")
                .append("총 정답 개수: ").append(correctWordCount).append("개\n\n[입력 시간 기록]\n");

        for (int i = 0; i < wordHistory.size(); i++) {
            history.append(wordHistory.get(i)).append(": ").append(timeHistory.get(i)).append("초\n");
        }
        return history.toString();
    }
}