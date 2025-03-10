package kh.edu.gameplay;

import java.util.Random;

public class WordManager {
    private final String[] words = {"apple", "banana", "cherry", "developer", "keyboard", "monitor", "notebook", "programming", "java", "spring"};
    private final Random random = new Random();
    private String currentWord;
    private long startTime;

    public String nextWord() {
        currentWord = words[random.nextInt(words.length)];
        startTime = System.currentTimeMillis();
        return currentWord;
    }

    public boolean checkWord(String input) {
        return input.equals(currentWord);
    }

    public double getTimeTaken() {
        return (System.currentTimeMillis() - startTime) / 1000.0;
    }
}