package edu.uga.cs.quiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    MainActivity ma = new MainActivity();
    TextView tv;
    private CountryData cd = null;
    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        cd = new CountryData( this );

        // date stuff
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String formattedDate= "Date: " + dateFormat.format(date);

        tv = (TextView) findViewById(R.id.resultTextView);
        tv.setText("Your result is: " + ma.score + "/6");

        String score = "Score: " + Integer.toString(ma.score);
        score += "/6";

        System.out.println("DATE: " +formattedDate);
        // new quiz into db
        quiz = new Quiz(formattedDate, score);
        new QuizDBWriterTask().execute(quiz);

        //obtain references to UI components
        Button mButtonNewQuiz = findViewById(R.id.butt_newQuiz);
        Button mButtonPastResults = findViewById(R.id.butt_pastResults);

        mButtonNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //create a new intent that starts the next activity when the button is pressed
                ma.score = 0;
                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        mButtonPastResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //create a new intent that starts the next activity when the button is pressed
                ma.score = 0;
                Intent intent = new Intent(ResultActivity.this, PastActivity.class);
                startActivity(intent);
            }
        });
    }   //onCreate

    // This is an AsyncTask class (it extends AsyncTask) to perform DB writing of a job lead, asynchronously.
    private class QuizDBWriterTask extends AsyncTask<Quiz, Void, Quiz> {

        // This method will run as a background process to write into db.
        @Override
        protected Quiz doInBackground( Quiz... quiz ) {
            cd.storeQuiz( quiz[0] );
            return quiz[0];
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.
        @Override
        protected void onPostExecute(Quiz quiz) {
            super.onPostExecute(quiz);

            // Show a quick confirmation
            Toast.makeText( getApplicationContext(), "Quiz created for " + quiz.getDate(),
                    Toast.LENGTH_SHORT).show();

            Log.d("ResultsActivity", "Quiz saved: " + quiz);
        }
    }

    @Override
    protected void onResume() {
        if( cd != null )
            cd.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if( cd != null )
            cd.close();
        super.onPause();
    }

    // The following activity callback methods are not needed and are for educational purposes only
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}