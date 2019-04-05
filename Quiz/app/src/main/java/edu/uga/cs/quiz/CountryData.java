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
 * This class is facilitates storing and restoring job leads stored.
 */
public class CountryData {

    public static final String DEBUG_TAG = "CountryData";
    private static Context myContext;

    // this is a reference to our database; it is used later to run SQL commands
    private SQLiteDatabase   db;
    private SQLiteOpenHelper countryDbHelper;
    private static final String[] allColumns = {
            CountryDBHelper.COUNTRY_COLUMN_ID,
            CountryDBHelper.COUNTRY_COLUMN_NAME,
            CountryDBHelper.COUNTRY_COLUMN_CONTINENT
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
            Log.d(DEBUG_TAG, "CountryData: db closed");
        }
    }

    public String getSpecificName(long id){
        String cName = CountryDBHelper.COUNTRY_COLUMN_NAME;
        String name = "";
        String query = "Select " + cName + " FROM " + CountryDBHelper.TABLE_COUNTRIES + " WHERE " + CountryDBHelper.COUNTRY_COLUMN_ID + " = " + "'" + id + "'";
        System.out.println(query);
        SQLiteDatabase db = countryDbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            name = cursor.getString( cursor.getColumnIndex( CountryDBHelper.COUNTRY_COLUMN_NAME ) );
            cursor.close();
            Log.d( DEBUG_TAG, "Retrieved Country name");
        }

        return name;
    }// get specific row

    public String getSpecificContinent(long id){
        String continent = "";
        String con = CountryDBHelper.COUNTRY_COLUMN_CONTINENT;

        String query = "Select " + con + " FROM " + CountryDBHelper.TABLE_COUNTRIES + " WHERE " + CountryDBHelper.COUNTRY_COLUMN_ID + " = " + "'" + id + "'";

        SQLiteDatabase db = countryDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if( cursor.getCount() > 0 ) {
            while( cursor.moveToNext() ) {
                long temp = cursor.getLong( cursor.getColumnIndex( CountryDBHelper.COUNTRY_COLUMN_ID ) );
                String name = cursor.getString( cursor.getColumnIndex( CountryDBHelper.COUNTRY_COLUMN_NAME ) );
                continent = cursor.getString( cursor.getColumnIndex( CountryDBHelper.COUNTRY_COLUMN_CONTINENT ) );

                Log.d( DEBUG_TAG, "Retrieved Country Continent");
            }
        }

        return continent;
    }// get specific row


    // Retrieve all job leads and return them as a List.
    // This is how we restore persistent objects stored as rows in the job leads table in the database.
    // For each retrieved row, we create a new JobLead (Java POJO object) instance and add it to the list.
    public List<Country> retrieveAllCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query( CountryDBHelper.TABLE_COUNTRIES, allColumns,
                    null, null, null, null, null );
            // collect all job leads into a List
            if( cursor.getCount() > 0 ) {
                while( cursor.moveToNext() ) {
                    long id = cursor.getLong( cursor.getColumnIndex( CountryDBHelper.COUNTRY_COLUMN_ID ) );
                    String name = cursor.getString( cursor.getColumnIndex( CountryDBHelper.COUNTRY_COLUMN_NAME ) );
                    String continent = cursor.getString( cursor.getColumnIndex( CountryDBHelper.COUNTRY_COLUMN_CONTINENT ) );

                    Country c = new Country( name, continent);
                    c.setId( id );
                    countries.add( c );
                    Log.d( DEBUG_TAG, "Retrieved Country: " + c );
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

    public Country storeCountry(Country country) {

        ContentValues values = new ContentValues();
        values.put( CountryDBHelper.COUNTRY_COLUMN_NAME, country.getName());
        values.put( CountryDBHelper.COUNTRY_COLUMN_CONTINENT, country.getContinent());

        // Insert the new row into the database table;  the id (primary key) will be
        // automatically generated by the database system
        long id = db.insert( CountryDBHelper.TABLE_COUNTRIES, null, values );

        // store the id in the JobLead instance, as it is now persistent
        country.setId( id );

        Log.d( DEBUG_TAG, "Stored new job lead with id: " + String.valueOf(country.getId() ) );

        return country;
    }


}
