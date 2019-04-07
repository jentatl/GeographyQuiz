package edu.uga.cs.geographyquiz;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Country country);

    @Query("Select * from countries_table ORDER BY country ASC")
    LiveData<List<Country>> getAllCountries();
}
