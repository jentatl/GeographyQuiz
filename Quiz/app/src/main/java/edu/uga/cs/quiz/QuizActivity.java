package edu.uga.cs.quiz;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static edu.uga.cs.quiz.R.id.radioButton1;

public class QuizActivity extends AppCompatActivity {

    ViewPager viewPager;
    QuizFragmentCollectionAdapter adapter;
    RadioGroup radioGroup;
    RadioButton b1;

    String question1 = "null";
    String question2 = "null";
    String question3 = "null";


    protected static final String [] CONTINENTS = {"Africa", "Asia", "Antartica", "Austrailia", "North America", "South America", "Europe" };

    protected static String [] countries = new String[6];
    protected static String [] continentAnswers = new String[6];
    protected static int [] continentNumberForm = new int [6];

    ArrayList <Integer> random7 = new ArrayList();
    ArrayList <Integer> csvList = new ArrayList();
    ArrayList <Integer> random3 = new ArrayList();

    CountryData cd = new CountryData(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // initiolizing lists, dont remove but not important to look at
        random7.add(1); random7.add(2); random7.add(3); random7.add(4); random7.add(5); random7.add(6); random7.add(7);
        random3.add(1); random3.add(2); random3.add(3);

        for(int i = 1; i < 196; i++){ csvList.add(i); }
        Collections.shuffle(csvList);
        Collections.shuffle(random7);
        Collections.shuffle(random3);
        // end initializing list

        /* IMPORTANT: THIS STUFF MUST GO FIRST */
        getSix(); // gets 6 countries, continents, and math.random

        // prints stuff out to make sure its right
        for(int i = 0; i < 6; i++){ System.out.println("Country: " + countries[i]); } System.out.println(" ");
        for(int i = 0; i < 6; i++) { System.out.println("Continent: " + continentAnswers[i]); } System.out.println(" ");

        checkAnswers(); // converts continents into number form
        /* END */

        viewPager = findViewById(R.id.pager);
        adapter = new QuizFragmentCollectionAdapter(getSupportFragmentManager());   //initialize the adapter
        viewPager.setAdapter(adapter);      //set adapter on viewPager


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //TODO Auto-generated method stub
            }

            @Override
            public void onPageSelected(int position) {
                //TODO Auto-generated method stub
                /*if(position == 6){      //if quiz is completed, show the result activity
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    startActivity(intent);
                }*/
                if(position < 6) {
                    viewPager.setCurrentItem(position, true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //TODO Auto-generated method stub
            }
        });
    } //onCreate

    // Gets 6 countries from the db and stores into array
    public void getSix(){
        for(int i = 0; i < 6; i++) {
            countries[i] = cd.getSpecificName(csvList.get(i));
            continentAnswers[i] = cd.getSpecificContinent(csvList.get(i));
        }// gets countries in array
    }// get six

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
                case "Austrailia":
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

        for(int i = 0; i < 6; i++){
            System.out.println("Countries Converted: " + continentNumberForm[i]);
        }
    }// check answers

    public void getAnswerOptions(int questionNum){
        int answer = 0;

        if(questionNum == 1){
            answer = continentNumberForm[0];
        }else if(questionNum == 2){
            answer = continentNumberForm[1];
        }else if(questionNum == 3){
            answer = continentNumberForm[2];
        }else if(questionNum == 4){
            answer = continentNumberForm[3];
        }else if(questionNum == 5){
            answer = continentNumberForm[4];
        }else if(questionNum == 6){
            answer = continentNumberForm[5];
        }


    }

    public int getRandomNumberAnswer(){
        int num = (int)(Math.random()*3) + 1;
        return num;
    }
    public String getCountry(int x){
        return countries[x];
    }

    public String getContinent(int x){
        return continentAnswers[x];
    }

    public int getContinentNum(int x){
        return continentNumberForm[x];
    }

}// class
