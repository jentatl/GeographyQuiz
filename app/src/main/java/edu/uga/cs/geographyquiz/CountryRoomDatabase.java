package edu.uga.cs.geographyquiz;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Country.class}, version = 1)
public abstract class CountryRoomDatabase extends RoomDatabase {
    public abstract CountryDao countryDao();

    private static volatile CountryRoomDatabase INSTANCE;   //makes initializing the database to be threadsafe

    //Make the CountryRoomDatabase a singleton to prevent having multiple
    //instances of the database opened at the same time.
    static CountryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CountryRoomDatabase.class) {
                if (INSTANCE == null) {
                    //Create database here
                    //Add the code to get a database. This code uses Room's database builder to create a
                    //RoomDatabase object in the application context from the CountryRoomDatabase class
                    //and names it "country_database"
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CountryRoomDatabase.class, "country_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
