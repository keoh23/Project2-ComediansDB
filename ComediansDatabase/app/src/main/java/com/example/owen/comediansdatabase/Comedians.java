package com.example.owen.comediansdatabase;

/**
 * Created by Owen on 9/6/2016.
 */
public class Comedians {

    long id;
    String name;
    String year;

    public Comedians(long id, String name, String year) {
        this.id = id;
        this.name = name;
        this.year = year;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Comedians{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
