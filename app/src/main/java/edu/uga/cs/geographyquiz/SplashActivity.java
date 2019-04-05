package edu.uga.cs.geographyquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SplashActivity extends AppCompatActivity {

    private Button display;
    final String TAG = "CVSReading";
    DBData dbData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //obtain references to UI components
        Button mButtonNewQuiz = findViewById(R.id.butt_newQuiz);
        Button mButtonPastResults = findViewById(R.id.butt_pastResults);


        display = (Button) findViewById(R.id.butt_newQuiz);

        dbData = new DBData(this);
        display.setOnClickListener(new ButtonClickListener());

       /* mButtonNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //create a new intent that starts the next activity when the button is pressed
                Intent intent = new Intent(SplashActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });*/

        mButtonPastResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //create a new intent that starts the next activity when the button is pressed
                Intent intent = new Intent(SplashActivity.this, PastResultsActivity.class);
                startActivity(intent);
            }
        });

    } // on create

    private class ButtonClickListener implements View.OnClickListener {
        public void onClick(View v) {
            /*try {
                Resources res = getResources();
                InputStream in_s = res.openRawResource(R.raw.country_continent);

                // read the CSV data
                CSVReader reader = new CSVReader(new InputStreamReader(in_s));
                String[] nextLine;

                int x = 1;

                  while ((nextLine = reader.readNext()) != null) {
                    int i2 = 0;
                    int y = 1;
                        // calls to write to database after setting up
                        Country country = new Country(nextLine[i2], nextLine[y]);
                        // sets everything

                        new CountriesDBWriterTask().execute(country);
                        x++;
                }
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }*/


            Country country = new Country("China", "Asia");
            new CountriesDBWriterTask().execute(country);
        } // on click
    }

    protected class CountriesDBWriterTask extends AsyncTask<Country, Void, Country> {
        // writes to database, asynch task
        protected Country doInBackground( Country... country ) {
            System.out.println("Country at 0: " + country[0]);
            dbData.storeCountry(country[0]);
            return country[0];
        }

        protected void onPostExecute(Country country){
            super.onPostExecute(country);

            // Show a quick confirmation
            Toast.makeText( getApplicationContext(), "Country created for " + country.getCountryName(),
                    Toast.LENGTH_SHORT).show();

            Log.d(TAG, "country saved: " + country);
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "NewJobLeadActivity.onResume()" );
        if(dbData != null )
            dbData.open();
        super.onResume();
    }

} // class
