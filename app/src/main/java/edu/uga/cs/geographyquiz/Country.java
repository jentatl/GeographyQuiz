package edu.uga.cs.geographyquiz;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "countries_table")
public class Country {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "country")
    public String countryName;

    @NonNull
    @ColumnInfo(name = "continent")
    public String continentName;

    public Country(String country, String continent) {
        countryName = country;
        continentName = continent;
    }

    public Country(){
        //required empty default constructor
    }
}
