package edu.uga.cs.quiz;

public class Country {

    private long   id;
    private String name;
    private String continent;

    /***
     * A class to handle country
     */
    public Country()
    {
        this.id = -1;
        this.name = null;
        this.continent = null;
    }

    public Country( String name, String continent) {
        this.id = -1;
        this.name = name;
        this.continent = continent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String toString() {
        return id + ": " + name + " " + continent;
    }
}
