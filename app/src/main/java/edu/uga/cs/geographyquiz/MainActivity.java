package edu.uga.cs.geographyquiz;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import com.opencsv.CSVReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    final String TAG = "CVSReading";

    private Button display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (Button) findViewById( R.id.button );
        display.setOnClickListener(new ButtonClickListener());
    }

    private class ButtonClickListener implements View.OnClickListener{
        public void onClick(View v) {
            try {
                TableLayout tableLayout = (TableLayout) findViewById(R.id.table_main);
                Resources res = getResources();
                InputStream in_s = res.openRawResource( R.raw.country_continent);

                // set up margins for each TextView in the table layout
                android.widget.TableRow.LayoutParams layoutParams =
                        new TableRow.LayoutParams( TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT );
                layoutParams.setMargins(20, 0, 20, 0);

                // read the CSV data
                CSVReader reader = new CSVReader(new InputStreamReader( in_s ) );
                String [] nextLine;
                while( ( nextLine = reader.readNext() ) != null ) {

                    // nextLine[] is an array of values from the line

                    // create the next table row for the layout
                    TableRow tableRow = new TableRow( getBaseContext() );
                    for( int i = 0; i < nextLine.length; i++ ) {

                        // create a new TextView and set its text
                        TextView textView = new TextView( getBaseContext() );
                        textView.setText( nextLine[i] + "index; " + i);

                        // add the new TextView to the table row in the table supplying the
                        // layout parameters
                        tableRow.addView( textView, layoutParams );
                        System.out.println("done");
                    }

                    // add the next row to the table layout
                    tableLayout.addView( tableRow );
                }
            } catch (Exception e) {
                Log.e( TAG, e.toString() );
            }
        } // on click
    } // class
} // class
