package com.revature.WebApp.repositories;

import com.revature.WebApp.entities.APICallsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Repository interface for CRUD access to the api_calls table
 */
@Repository
public interface ApiCallRepository extends JpaRepository<APICallsEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT count(*) FROM api_calls a WHERE a.api = ?1 AND EXTRACT(MONTH FROM a.date) = ?2")
    Integer getApiCallsThisMonth(String api, Integer month);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM api_calls a WHERE a.api = ?1 AND a.date = ?2")
    Integer getApiCallsToday(String api, LocalDate date);
}
