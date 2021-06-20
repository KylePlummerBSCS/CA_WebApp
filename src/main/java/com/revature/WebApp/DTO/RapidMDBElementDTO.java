package com.revature.WebApp.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


/**
 * DTO for the movie elements that are part of the RapidMDb title search results
 */
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class RapidMDBElementDTO {
    private String title;
    private String year;
    private String imdbID;
    private String type;
    private String poster;


    public RapidMDBElementDTO() {

    }

    public RapidMDBElementDTO(String title, String year, String imdb, String type, String poster) {
        this.title = title;
        this.year = year;
        this.imdbID = imdb;
        this.type = type;
        this.poster = poster;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "RapidMDBElementDTO{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
