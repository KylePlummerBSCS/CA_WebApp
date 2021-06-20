package com.revature.WebApp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity representing a row in the watch_history table, for maintaining a list of movies a user has seen, as well
 * as review and score.
 */
@Entity
@Table(name = "watch_history")
public class WatchHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Integer historyId;

    @NotNull
    @ManyToOne(targetEntity = UserEntity.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity userId;

    @NotNull
    @ManyToOne(targetEntity = MovieDetailsEntity.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_movie_id", nullable = false)
    private MovieDetailsEntity movieId;

    private Integer score;

    @Column(length=1000)
    private String review;


    public WatchHistoryEntity() {
    }

    public WatchHistoryEntity(UserEntity userId, MovieDetailsEntity movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public MovieDetailsEntity getMovieId() {
        return movieId;
    }

    public void setMovieId(MovieDetailsEntity movieId) {
        this.movieId = movieId;
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

    @Override
    public String toString() {
        return "WatchHistoryEntity{" +
                "historyId=" + historyId +
                ", userId=" + userId +
                ", movieId=" + movieId +
                ", score=" + score +
                ", review='" + review + '\'' +
                '}';
    }
}
