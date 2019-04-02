package edu.uga.cs.geographyquiz;

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
    }
}
