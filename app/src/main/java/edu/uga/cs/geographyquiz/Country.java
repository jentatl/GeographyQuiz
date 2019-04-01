package edu.uga.cs.geographyquiz;

public class Country {

    private int countryId;
    private String countryName;
    private String countryContinent;

    //constructors
    public Country(){}
    public Country(int id, String country, String continent){
        this.countryId = id;
        this.countryName = country;
        this.countryContinent = continent;
    }

    //properties
    public void setId(int id){
        this.countryId = id;
    }

    public int getId(){
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
}
