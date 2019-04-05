package edu.uga.cs.quiz;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    ViewPager viewPager;
    QuizFragmentCollectionAdapter adapter;

    protected static final String [] CONTINENTS = {"Africa", "Asia", "Antartica", "Austrailia", "North America", "South America", "Europe" };

    protected static String [] countries = new String[6];
    protected static String [] continentAnswers = new String[6];

    CountryData cd = new CountryData(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

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

        getSix();

        for(int i = 0; i < 6; i++){
            System.out.println("Country: " + countries[i]);
            System.out.println("Continent: " + continentAnswers[i]);
        }
    } //onCreate

    // Gets 6 countries from the db and stores into array
    public void getSix(){
        int random [] = new int[6];

        boolean flag = true;

        for(int i = 0; i < 6; i++){
          int j = (int)(Math.random() * 194) +1;
          random[i] = j;
        }

        for(int i = 0; i < 6; i++) {
            countries[i] = cd.getSpecificName(random[i]);
            continentAnswers[i] = cd.getSpecificContinent(random[i]);
        }// for

    }// get six
}
