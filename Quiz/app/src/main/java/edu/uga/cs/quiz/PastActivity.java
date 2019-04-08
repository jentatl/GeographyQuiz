package edu.uga.cs.quiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class PastActivity extends AppCompatActivity {

        public static final String DEBUG_TAG = "PastQuizActivity";

        private RecyclerView recyclerView;
        private RecyclerView.LayoutManager layoutManager;
        private RecyclerView.Adapter recyclerAdapter;

        private CountryData quizData = null;
        private List<Quiz> quizList;

        @Override
        protected void onCreate( Bundle savedInstanceState ) {

            Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onCreate()" );

            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_past_quiz );

            recyclerView = (RecyclerView) findViewById( R.id.recyclerView );

            // use a linear layout manager for the recycler view
            layoutManager = new LinearLayoutManager(this );
            recyclerView.setLayoutManager( layoutManager );

            // Create a JobLeadsData instance, since we will need to save a new JobLead to the dn.
            // Note that even though more activites may create their own instances of the JobLeadsData
            // class, we will be using a single instance of the JobLeadsDBHelper object, since
            // that class is a singleton class.
            quizData= new CountryData( this );

            // Execute the retrieval of the job leads in an asynchronous way,
            // without blocking the UI thread.
            new PastActivityDBReaderTask().execute();

            Button mButtonNewQuiz = findViewById(R.id.butt_newQuiz);
            Button mButtonPastResults = findViewById(R.id.butt_pastResults);

            mButtonNewQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {   //create a new intent that starts the next activity when the button is pressed
                    Intent intent = new Intent(PastActivity.this, QuizActivity.class);
                    startActivity(intent);
                }
            });

            mButtonPastResults.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {   //create a new intent that starts the next activity when the button is pressed
                    Intent intent = new Intent(PastActivity.this, PastActivity.class);
                    startActivity(intent);
                }
            });

        }

        // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of job leads, asynchronously.
        private class PastActivityDBReaderTask extends AsyncTask<Void, Void, List<Quiz>> {

            // This method will run as a background process to read from db.
            @Override
            protected List<Quiz> doInBackground( Void... params ) {
                quizData.open();
                quizList = quizData.retrieveAllQuiz();

                Log.d( DEBUG_TAG, "QuizListTask: received " + quizList.size() );

                return quizList;
            }

            // This method will be automatically called by Android once the db reading
            // background process is finished.  It will then create and set an adapter to provide
            // values for the RecyclerView.
            @Override
            protected void onPostExecute( List<Quiz> quizList ) {
                super.onPostExecute(quizList);
                recyclerAdapter = new QuizRecyclerAdapter(quizList);
                recyclerView.setAdapter( recyclerAdapter );
            }
        }

        @Override
        protected void onResume() {
            Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onResume()" );
            if( quizData != null )
                quizData.open();
            super.onResume();
        }

        @Override
        protected void onPause() {
            Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onPause()" );
            if( quizData != null )
                quizData.close();
            super.onPause();
        }

        // These activity callback methods are not needed and are for edational purposes only
        @Override
        protected void onStart() {
            Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onStart()" );
            super.onStart();
        }

        @Override
        protected void onStop() {
            Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onStop()" );
            super.onStop();
        }

        @Override
        protected void onDestroy() {
            Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onDestroy()" );
            super.onDestroy();
        }

        @Override
        protected void onRestart() {
            Log.d( DEBUG_TAG, "ReviewJobLeadsActivity.onRestart()" );
            super.onRestart();
        }
}
