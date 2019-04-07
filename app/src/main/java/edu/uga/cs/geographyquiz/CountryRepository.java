package edu.uga.cs.geographyquiz;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.lifecycle.LiveData;

public class CountryRepository {

    //Add member variables for the DAO and the list of countries
    private CountryDao mCountryDao;
    private LiveData<List<Country>> mAllCountries;

    //Add a constructor that gets a handle to the database and initializes the member variables.
    public CountryRepository(Application application){
        CountryRoomDatabase db = CountryRoomDatabase.getDatabase(application);
        mCountryDao = db.countryDao();
        mAllCountries = mCountryDao.getAllCountries();
    }

    //Add a wrapper for getAllCountries(). Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Country>> getAllCountries(){
        return mAllCountries;
    }

    //Add a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
    // Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
    public void insert(Country country){
        new insertAsyncTask(mCountryDao).execute(country);
    }

    private static class insertAsyncTask extends AsyncTask<Country, Void, Void> {

        private CountryDao mAsyncTaskDao;

        insertAsyncTask(CountryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Country... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public CountryRepository(){

        HashMap<String, String> mappyWappy = new HashMap<String, String>();


    }

    public List<Country> getDummyCountries(){
        ArrayList<Country> mappyWappy = new ArrayList<Country>();

        mappyWappy.add(new Country ("Chad", "Africa"));
        mappyWappy.add(new Country("China", "Asia"));
        mappyWappy.add(new Country("UwU", "OwO"));
        mappyWappy.add(new Country("Uganda", "Africa"));
        mappyWappy.add(new Country("Ecuador", "South America"));
        mappyWappy.add(new Country("Trinidad and Tobago", "North America"));

        return mappyWappy;
    }

    public String[] getDummyAnswers(){
        return new String[]{"Mexico", "OwO", "North America"};
    }

}


