package edu.uga.cs.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Handles main activity
 */
public class MainActivity extends AppCompatActivity {

    private Button newQuiz;
    private Button past;

    public static final String DEBUG_TAG = "MainActivity";
    public static int score = 0;

    private CountryData countryData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newQuiz = (Button) findViewById(R.id.butt_newQuiz);
        past = (Button) findViewById(R.id.butt_pastResults);
        countryData = new CountryData( this );

        if(!fileExists(this, "/data/data/edu.uga.cs.quiz/databases/countries.db")){
            try {
                Resources res = getResources();
                InputStream in_s = res.openRawResource(R.raw.country_continent);

                // read the CSV data
                CSVReader reader = new CSVReader(new InputStreamReader(in_s));
                String[] nextLine;

                while ((nextLine = reader.readNext()) != null) {
                    // calls to write to database after setting up
                    Country country = new Country(nextLine[0], nextLine[1]);
                    // sets everything
                    new CountryDBWriterTask().execute(country);
                }
            } catch (Exception e) {
                Log.e(DEBUG_TAG, e.toString());

            }
        }else{
            // do nothing
        }
        newQuiz.setOnClickListener( new QuizClickListener()) ;
        past.setOnClickListener( new PastClickListener()) ;
    }

    private class PastClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, PastActivity.class);
            startActivity(intent);

        }
    }

    private class QuizClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

         /* try {
                Resources res = getResources();
                InputStream in_s = res.openRawResource(R.raw.country_continent);

                // read the CSV data
                CSVReader reader = new CSVReader(new InputStreamReader(in_s));
                String[] nextLine;

                while ((nextLine = reader.readNext()) != null) {
                    // calls to write to database after setting up
                    Country country = new Country(nextLine[0], nextLine[1]);
                    // sets everything
                    new CountryDBWriterTask().execute(country);
                }
            } catch (Exception e) {
                Log.e(DEBUG_TAG, e.toString());

            }*/

           Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);

        }// on click view
    }

    public boolean fileExists(Context context, String filename) {
        File file = new File(filename);
        if(file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB writing of a job lead, asynchronously.
    private class CountryDBWriterTask extends AsyncTask<Country, Void, Country> {

        // This method will run as a background process to write into db.
        @Override
        protected Country doInBackground( Country... country ) {
            countryData.storeCountry( country[0] );
            return country[0];
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.
        @Override
        protected void onPostExecute( Country country ) {
            super.onPostExecute( country );

            // Show a quick confirmation
            Toast.makeText( getApplicationContext(), "Country created for " + country.getName(),
                    Toast.LENGTH_SHORT).show();

            Log.d( DEBUG_TAG, "Country saved: " + country );
        }
    }

    @Override
    protected void onResume() {
        if( countryData != null )
            countryData.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if( countryData != null )
            countryData.close();
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
