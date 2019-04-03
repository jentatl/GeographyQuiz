package edu.uga.cs.geographyquiz;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class QuizActivity extends AppCompatActivity {

    ViewPager viewPager;
    QuizFragmentCollectionAdapter adapter;

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

    }   //onCreate
}
