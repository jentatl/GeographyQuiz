package edu.uga.cs.geographyquiz;

public class Quiz {

    private int quizId;
    private int quizScore;
    private String quizDate;
    private String countries;

    //constructors
    public Quiz(){}
    public Quiz(int id, int score, String date, String countries){
        this.quizId=  id;
        this.quizScore = score;
        this.quizDate = date;
        this.countries = countries;
    }

    //properties
    public void setId(int id){
        this.quizId = id;
    }

    public int getId(){
        return this.quizId;
    }

    public void setScore(int score){
        this.quizScore = score;
    }

    public int getScore(){
        return this.quizScore;
    }

    public String getDate(){
        return this.quizDate;
    }

    public void setDate(String date){

        this.quizDate = date;
    }

    public String getCountries(){
        return this.countries;
    }

    public void setCountries(String countries){
        this.countries = countries;
    }
}
