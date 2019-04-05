package edu.uga.cs.geographyquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBData{
    public final String DEBUG_TAG = "DBDATA";
    public SQLiteDatabase db;
    public SQLiteOpenHelper databaseHandler;

    public static final String[] countriesColumns = {
            DatabaseHandler.KEY_COUNTRIES_ID,
            DatabaseHandler.KEY_COUNTRIES_NAME,
            DatabaseHandler.KEY_COUNTRY_CONTINENT

    };

    public static final String[] quizColumns = {
            DatabaseHandler.KEY_QUIZZES_ID,
            DatabaseHandler.KEY_QUIZ_DATE,
            DatabaseHandler.KEY_QUIZ_SCORE,
            DatabaseHandler.KEY_QUIZ_COUNTRIES
    };

    public DBData(Context context){
        this.databaseHandler = DatabaseHandler.getInstance(context);
    }

    // Open the database
    public void open() {
        db = databaseHandler.getWritableDatabase();
        Log.d( DEBUG_TAG, "DBData: db open" );
    }

    // Close the database
    public void close() {
        if( databaseHandler!= null ) {
            databaseHandler.close();
            Log.d(DEBUG_TAG, "DBData: db closed");
        }
    }

    // Retrieve all countries and return them as a List.
    // This is how we restore persistent objects stored as rows in the job leads table in the database.
    public List<Country> retrieveAllCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query(DatabaseHandler.TABLE_COUNTRIES, countriesColumns,
                    null, null, null, null, null );
            // collect all countries into a List
            if( cursor.getCount() > 0 ) {
                while( cursor.moveToNext() ) {
                    int id = cursor.getInt( cursor.getColumnIndex( DatabaseHandler.KEY_COUNTRIES_ID) );
                    String name = cursor.getString( cursor.getColumnIndex( DatabaseHandler.KEY_COUNTRIES_NAME) );
                    String continent = cursor.getString( cursor.getColumnIndex( DatabaseHandler.KEY_COUNTRY_CONTINENT) );
                    Country country= new Country(name, continent);
                    country.setId( id );
                    countries.add(country);
                    Log.d( DEBUG_TAG, "Retrieved country: " + country );
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
        return countries;
    }

    // Retrieve all quizzes and return them as a List.
    // This is how we restore persistent objects stored as rows in the job leads table in the database.
    public List<Quiz> retrieveAllQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query(DatabaseHandler.TABLE_QUIZZES, quizColumns,
                    null, null, null, null, null );
            // collect all job leads into a List
            if( cursor.getCount() > 0 ) {
                while( cursor.moveToNext() ) {
                    int id = cursor.getInt( cursor.getColumnIndex( DatabaseHandler.KEY_QUIZZES_ID) );
                    String date = cursor.getString( cursor.getColumnIndex( DatabaseHandler.KEY_QUIZ_DATE) );
                    int score = cursor.getInt(cursor.getColumnIndex(DatabaseHandler.KEY_QUIZ_SCORE));
                    String continent = cursor.getString( cursor.getColumnIndex( DatabaseHandler.KEY_QUIZ_COUNTRIES) );
                    Quiz quiz = new Quiz(id, score, date, continent);
                    quiz.setId( id );
                    quizzes.add(quiz);
                    Log.d( DEBUG_TAG, "Retrieved quiz: " + quiz );
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

    // Store a new country in the database.
    public Country storeCountry(Country country) {

        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the country argument.
        // This is how we are providing persistence to a country instance
        // by storing it as a new row in the database table representing countries
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_COUNTRIES_NAME, country.getCountryName());
        values.put(DatabaseHandler.KEY_COUNTRY_CONTINENT, country.getContinent());
        System.out.println("Country name " +  country.getCountryName());
        System.out.println("Country contintent " +  country.getContinent());

        // Insert the new row into the database table;  the id (primary key) will be
        // automatically generated by the database system

        long id = db.insert(DatabaseHandler.TABLE_COUNTRIES, null, values );


        // store the id in the JobLead instance, as it is now persistent
        country.setId(id);

        Log.d( DEBUG_TAG, "Stored new country with id: " + String.valueOf(country.getId() ) );

        return country;
    }


    // Store a new quiz in the database.
    public Quiz storeQuiz(Quiz quiz) {

        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the quioz argument.
        // This is how we are providing persistence to a quiz instance
        // by storing it as a new row in the database table representing countries
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_QUIZ_DATE, quiz.getDate());
        values.put(DatabaseHandler.KEY_QUIZ_SCORE, quiz.getScore());
        values.put(DatabaseHandler.KEY_QUIZ_COUNTRIES, quiz.getCountries());

        // Insert the new row into the database table;  the id (primary key) will be
        // automatically generated by the database system
        long id = db.insert(DatabaseHandler.TABLE_QUIZZES, null, values );


        // store the id in the JobLead instance, as it is now persistent
        quiz.setId((int)id);

        Log.d( DEBUG_TAG, "Stored new country with id: " + String.valueOf(quiz.getId() ) );

        return quiz;
    }

}
