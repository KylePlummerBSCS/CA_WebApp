package com.revature.WebApp.AllSamples.repositories;

import com.revature.WebApp.AllSamples.entities.Baskets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketsRepository extends JpaRepository<Baskets, Integer> {
}
