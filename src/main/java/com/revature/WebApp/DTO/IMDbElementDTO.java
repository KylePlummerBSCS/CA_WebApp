package com.revature.WebApp.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class IMDbElementDTO {
    
    private String imdbId;
    private String title;
    private String year;
    
    public IMDbElementDTO(){
    
    }
    
    public IMDbElementDTO(String imdbId,String title,String year) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
    }
    
    public String getImdbId() {
        return imdbId;
    }
    
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
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
    
    @Override
    public String toString() {
        return "IMDbElementDTO{" +
               "imdbId='" + imdbId + '\'' +
               ", title='" + title + '\'' +
               ", year='" + year + '\'' +
               '}';
    }
}
