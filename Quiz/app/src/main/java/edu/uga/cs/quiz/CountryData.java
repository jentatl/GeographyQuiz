package edu.uga.cs.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is facilitates storing all data
 */
public class CountryData {

    public static final String DEBUG_TAG = "CountryData";
    private static Context myContext;

    // this is a reference to our database; it is used later to run SQL commands
    private SQLiteDatabase db;
    private SQLiteOpenHelper countryDbHelper;

    private static final String[] allColumns = {
            CountryDBHelper.COUNTRY_COLUMN_ID,
            CountryDBHelper.COUNTRY_COLUMN_NAME,
            CountryDBHelper.COUNTRY_COLUMN_CONTINENT
    };

    private static final String[] allQuiz = {
            CountryDBHelper.QUIZ_COLUMN_ID,
            CountryDBHelper.QUIZ_COLUMN_DATE,
            CountryDBHelper.QUIZ_COLUMN_SCORE
    };

    public CountryData( Context context ) {
        this.countryDbHelper = CountryDBHelper.getInstance( context );
        this.myContext = context;
    }

    // Open the database
    public void open() {
        db = countryDbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "CountryData: db open" );
    }

    // Close the database
    public void close() {
        if( countryDbHelper != null ) {
            countryDbHelper.close();

        }
    }

    public String getSpecificName(long id){
        String name = "";
        String query = "Select " + CountryDBHelper.COUNTRY_COLUMN_NAME + " FROM " + CountryDBHelper.TABLE_COUNTRIES + " WHERE " + CountryDBHelper.COUNTRY_COLUMN_ID +  "=" + id + ";";
        System.out.println(query);
        SQLiteDatabase db = countryDbHelper.getWritableDatabase();

        Cursor cursor = null;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query(CountryDBHelper.TABLE_COUNTRIES, allColumns,
                    null, null, null, null, null );
            // collect all job leads into a List
            if( cursor.getCount() > 0 ) {
                cursor.moveToPosition((int)id);
                    name = cursor.getString( cursor.getColumnIndex(CountryDBHelper.COUNTRY_COLUMN_NAME ));
            }

        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return name;
    }// get specific row

    public String getSpecificContinent(long id){
        String continent = "";
        String con = CountryDBHelper.COUNTRY_COLUMN_CONTINENT;

        String query = "Select " + con + " FROM " + CountryDBHelper.TABLE_COUNTRIES + " WHERE " + CountryDBHelper.COUNTRY_COLUMN_ID + " = " + "'" + id + "'";

        SQLiteDatabase db = countryDbHelper.getWritableDatabase();
        Cursor cursor = null;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query(CountryDBHelper.TABLE_COUNTRIES, allColumns,
                    null, null, null, null, null );
            // collect all job leads into a List
            if( cursor.getCount() > 0 ) {
                cursor.moveToPosition((int)id);
                continent = cursor.getString( cursor.getColumnIndex(CountryDBHelper.COUNTRY_COLUMN_CONTINENT));
            }

        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return continent;
    }// get specific row

    public List<Quiz> retrieveAllQuiz() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query( CountryDBHelper.TABLE_QUIZ, allQuiz,
                    null, null, null, null, null );
            // collect all job leads into a List
            if( cursor.getCount() > 0 ) {
                while( cursor.moveToNext() ) {
                    long id = cursor.getLong( cursor.getColumnIndex( CountryDBHelper.QUIZ_COLUMN_ID ) );
                    String date = cursor.getString( cursor.getColumnIndex( CountryDBHelper.QUIZ_COLUMN_DATE) );
                    String score = cursor.getString( cursor.getColumnIndex( CountryDBHelper.QUIZ_COLUMN_SCORE) );

                    Quiz q = new Quiz(date, score);
                    q.setId( id );
                    quizzes.add(q);
                    Log.d( DEBUG_TAG, "Retrieved Quiz: " + q );
                }
            }
            Log.d( DEBUG_TAG, "Number of records from DB: " + cursor.getCount() );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return quizzes;
    }

    public Quiz storeQuiz(Quiz quiz){
        ContentValues values = new ContentValues();
        values.put( CountryDBHelper.QUIZ_COLUMN_DATE, quiz.getDate());
        values.put( CountryDBHelper.QUIZ_COLUMN_SCORE, quiz.getScore());

        db = countryDbHelper.getWritableDatabase();

        // Insert the new row into the database table;  the id (primary key) will be
        // automatically generated by the database system
        long id = db.insert( CountryDBHelper.TABLE_QUIZ, null, values );

        // store the id in the JobLead instance, as it is now persistent
        quiz.setId( id );

        return quiz;
    }

    public Country storeCountry(Country country) {

        ContentValues values = new ContentValues();
        values.put( CountryDBHelper.COUNTRY_COLUMN_NAME, country.getName());
        values.put( CountryDBHelper.COUNTRY_COLUMN_CONTINENT, country.getContinent());

        db = countryDbHelper.getWritableDatabase();
        // Insert the new row into the database table;  the id (primary key) will be
        // automatically generated by the database system
        long id = db.insert( CountryDBHelper.TABLE_COUNTRIES, null, values );

        // store the id in the JobLead instance, as it is now persistent
        country.setId( id );

        Log.d( DEBUG_TAG, "Stored new country with id: " + String.valueOf(country.getId()));

        return country;
    }
}
