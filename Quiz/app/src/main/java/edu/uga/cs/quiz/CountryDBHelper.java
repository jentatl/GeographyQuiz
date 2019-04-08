package edu.uga.cs.quiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;


/**
 * Helps to delete and upgrade database
 */
public class CountryDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "CuntriesDBHelper";

    public static final String DB_NAME = "countries.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_COUNTRIES = "countries";
    public static final String COUNTRY_COLUMN_ID = "_id";
    public static final String COUNTRY_COLUMN_NAME = "name";
    public static final String COUNTRY_COLUMN_CONTINENT = "continent";
    public static final String TABLE_QUIZ = "quiz";
    public static final String QUIZ_COLUMN_ID = "_id";
    public static final String QUIZ_COLUMN_DATE = "date";
    public static final String QUIZ_COLUMN_SCORE = "score";

    // This is a reference to the only instance for the helper.
    private static CountryDBHelper helperInstance;

    private static final String CREATE_COUNTRIES =
            "create table " + TABLE_COUNTRIES + " ("
                    + COUNTRY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COUNTRY_COLUMN_NAME + " TEXT, "
                    + COUNTRY_COLUMN_CONTINENT + " TEXT)";


    private static final String CREATE_QUIZ =
            "create table " + TABLE_QUIZ+ " ("
                    + QUIZ_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUIZ_COLUMN_DATE + " TEXT, "
                    + QUIZ_COLUMN_SCORE + " TEXT)";

    private CountryDBHelper( Context context ) {

        super( context, DB_NAME, null, DB_VERSION );
    }

    public static synchronized CountryDBHelper getInstance( Context context ) {
        // check if the instance already exists and if not, create the instance
        if( helperInstance == null ) {
            helperInstance = new CountryDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_COUNTRIES );
        db.execSQL(CREATE_QUIZ);
        Log.d( DEBUG_TAG, "Table " + TABLE_COUNTRIES +  " + " + TABLE_QUIZ +  " created" );
    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {

    }
}
