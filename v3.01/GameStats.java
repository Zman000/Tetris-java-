public class GameStats {
    private int score = 0;
    private int linesCleared = 0;
    private int level = 1;

    public void addLines(int lines) {
        linesCleared += lines;
        switch (lines) {
            case 1 -> score += 100;
            case 2 -> score += 300;
            case 3 -> score += 500;
            case 4 -> score += 800;
        }

        // Optional level-up every 10 lines
        level = 1 + linesCleared / 5;
    }

    public int getScore() { return score; }
    public int getLinesCleared() { return linesCleared; }
    public int getLevel() { return level; }
}