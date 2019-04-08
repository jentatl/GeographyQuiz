package edu.uga.cs.quiz;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class QuizActivity extends AppCompatActivity {

    protected static final String [] CONTINENTS = {"Africa", "Asia", "Antartica", "Oceania", "North America", "South America", "Europe" };

    protected static String [] countries = new String[6];
    protected static String [] continentAnswers = new String[6];
    protected static int [] continentNumberForm = new int [6];

    protected ArrayList <Integer> random7 = new ArrayList();
    protected ArrayList <Integer> csvList = new ArrayList();
    protected ArrayList <Integer> random3 = new ArrayList();
    protected ArrayList <Integer> questionNum = new ArrayList();

    MainActivity ma = new MainActivity();

    CountryData cd = new CountryData(this);
    static final int NUM_ITEMS = 6;

    static public int count = 0;
    QuizFragmentCollectionAdapter mAdapter;

    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        // initiolizing lists, dont remove but not important to look at
        random7.add(1); random7.add(2); random7.add(3); random7.add(4); random7.add(5); random7.add(6); random7.add(7);
        random3.add(1); random3.add(2); random3.add(3);

        for(int i = 1; i < 195; i++){ csvList.add(i); }
        Collections.shuffle(csvList);
        Collections.shuffle(random7);
        Collections.shuffle(random3);
        // end initializing list

        /* IMPORTANT: THIS STUFF MUST GO FIRST */
        getSix();
        checkAnswers();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mAdapter = new QuizFragmentCollectionAdapter(getSupportFragmentManager());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
    }

    public void getSix(){
        for(int i = 0; i < 6; i++) {
            countries[i] = cd.getSpecificName(csvList.get(i));
            continentAnswers[i] = cd.getSpecificContinent(csvList.get(i));
        }// gets countries in array
    }// get six

    public String getCountry(int x){
        return countries[x];
    }

    public String getAnswer(int questionNum){
        String continent = "";

        continent = CONTINENTS[continentNumberForm[questionNum]];

        return continent;
    }// get answer

    public int getNumCorrectAnswer(int questionNum){
        return continentNumberForm[questionNum];
    }
    public void checkAnswers(){
        for(int i = 0; i < 6; i++){
            switch(continentAnswers[i]){
                case "Africa":
                    continentNumberForm[i] = 0;
                    break;
                case "Asia":
                    continentNumberForm[i] = 1;
                    break;
                case "Antartica":
                    continentNumberForm[i] = 2;
                    break;
                case "Oceania":
                    continentNumberForm[i] = 3;
                    break;
                case "North America":
                    continentNumberForm[i] = 4;
                    break;
                case "South America":
                    continentNumberForm[i] = 5;
                    break;
                case "Europe":
                    continentNumberForm[i] = 6;
                    break;
            } // switch
        }// for
    }// check answers

    public boolean checkAnswer(int question, String continent, int continentNum, long answer){
        System.out.println("Right Continent: " + continent);
        System.out.println("Right Continent: " + continentNum);
        System.out.println("Selected: " + answer);

        boolean flag = false;

        if(continentNum != (int)answer){ // if answer is wrong
            // nothing happens
        }else{ // if answer is right
            ma.score++;
            flag = true;
        }

        System.out.println("Score: " + ma.score);
        return flag;
    }
}// class
