import java.io.Serializable;

/**
 * CS180 Project 4
 * @author Alex Crick L06
 * @author Columbus Holt L06
 */

public class Result implements Serializable, Comparable<Result>{
    private int score;
    public static final long serialVersionUID = -938761094876384658L;
    private String url;
    private int urlID;

    public Result(String url, int urlID) {
        this.url = url;
        this.urlID = urlID;
        this.score = 1;
    }

    @Override
    public int compareTo(Result o) {
        if (o.score > score)
            return 1;
        if (o.score < score)
            return -1;
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Result && ((Result) obj).getURLID() == getURLID() && ((Result) obj).score == getScore());
    }

    public int getScore() {
        return score;
    }

    public String getURL() {
        return url;
    }

    public int getURLID() {
        return urlID;
    }

    synchronized void incrementScore() {
        score++;
    }

    void updateScore(int score) {
        this.score += score;
    }
}