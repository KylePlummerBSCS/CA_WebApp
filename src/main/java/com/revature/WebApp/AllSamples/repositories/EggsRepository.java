package com.revature.WebApp.AllSamples.repositories;

import com.revature.WebApp.AllSamples.entities.Eggs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EggsRepository extends JpaRepository<Eggs, Integer> {
}
