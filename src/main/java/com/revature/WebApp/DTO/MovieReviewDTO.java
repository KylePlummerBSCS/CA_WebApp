package com.revature.WebApp.DTO;

public class MovieReviewDTO {
    private String imdbId;
    private Integer score;
    private String review;

    public MovieReviewDTO() {
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
