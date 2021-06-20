package com.revature.WebApp.entities;


import com.revature.WebApp.DTO.OMDbSearchResultsDTO;

import javax.persistence.*;

/**
 * Entity representing a row in the movie_cache table.
 * This table stores info on the movie details previously retrieved, as they are mostly static we
 * can keep them here to reduce future calls to OMDb and IMDb.
 */
@Entity
@Table(name="movie_cache")
public class MovieDetailsEntity {
    @Id
    private String imdbId;

    private String title;
    private Integer Year;//???
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;

    @Column(length=1000)
    private String writer;

    @Column(length=1000)
    private String actors;

    @Column(length=1000)
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String type;
    private String dvd;
    private String boxOffice;
    private String production;
    private String website;

    public MovieDetailsEntity() {

    }

    public MovieDetailsEntity(OMDbSearchResultsDTO searchResults) {
        this.imdbId = searchResults.getImdbID();
        this.title = searchResults.getTitle();
        this.Year = Integer.parseInt(searchResults.getYear());
        this.rated = searchResults.getRated();
        this.released = searchResults.getReleased();
        this.runtime = searchResults.getRuntime();
        this.genre = searchResults.getGenre();
        this.director = searchResults.getDirector();
        this.writer = searchResults.getWriter();
        this.actors = searchResults.getActors();
        this.plot = searchResults.getPlot();
        this.language = searchResults.getLanguage();
        this.country = searchResults.getCountry();
        this.awards = searchResults.getAwards();
        this.poster = searchResults.getPoster();
        this.metascore = searchResults.getMetascore();
        this.imdbRating = searchResults.getImdbRating();
        this.imdbVotes = searchResults.getImdbVotes();
        this.type = searchResults.getType();
        this.dvd = searchResults.getDvd();
        this.boxOffice = searchResults.getBoxOffice();
        this.production = searchResults.getProduction();
        this.website = searchResults.getWebsite();
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

//    public String getYear() {
//        return Year;
//    }
//
//    public void setYear(String year) {
//        Year = year;
//    }



    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDvd() {
        return dvd;
    }

    public void setDvd(String dvd) {
        this.dvd = dvd;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
