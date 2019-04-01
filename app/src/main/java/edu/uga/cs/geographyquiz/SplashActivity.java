package edu.uga.cs.geographyquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //obtain references to UI components
        Button mButtonNewQuiz = findViewById(R.id.butt_newQuiz);
        Button mButtonPastResults = findViewById(R.id.butt_pastResults);

        mButtonNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //create a new intent that starts the next activity when the button is pressed
                Intent intent = new Intent(SplashActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        mButtonPastResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //create a new intent that starts the next activity when the button is pressed
                Intent intent = new Intent(SplashActivity.this, PastResultsActivity.class);
                startActivity(intent);
            }
        });

    }
}
