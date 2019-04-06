package edu.uga.cs.quiz;

public class Score {

    public int score;

    public Score(){
        score = 0;
    }

    public void updateScore(){
        this.score ++;
    }

    public int getScore(){
        return this.score;
    }
}
