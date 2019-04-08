package edu.uga.cs.quiz;

public class Quiz {
    private long   id;
    private String date;
    private String score;

    public Quiz()
    {
        this.id = -1;
        this.date = null;
        this.score = null;
    }

    public Quiz(String date, String score) {
        this.date = date;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String toString() {
        return id + ": " + date + " " + score;
    }
}

