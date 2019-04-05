package edu.uga.cs.geographyquiz;

/**
 * POJO Class
 */
public class Country {

    private long countryId;
    private String countryName;
    private String countryContinent;

    //constructors

    public Country(){
        this.countryName = null;
        this.countryContinent = null;
        this.countryId = -1;
    }
    public Country(String country, String continent){
        this.countryName = country;
        this.countryContinent = continent;
        this.countryId = -1;
    }

    //properties
    public void setId(long id){
        this.countryId = id;
    }

    public long getId(){
        return this.countryId;
    }

    public void setContinent(String continent){
        this.countryContinent = continent;
    }

    public String getContinent(){
        return this.countryContinent;
    }

    public void setCountryName(String name){
        this.countryName = name;
    }

    public String getCountryName(){
        return this.countryName;
    }

    public String toString(){
        return countryId + ": " + countryName + " " + countryContinent;
    }
}
