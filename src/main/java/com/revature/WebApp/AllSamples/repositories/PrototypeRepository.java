package com.revature.WebApp.AllSamples.repositories;

import com.revature.WebApp.AllSamples.entities.PrototypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Empty repository is needed to autowire repo object that can invoke CRUD on entity.
 * Just extend JpaRepository and include your entity type and the type of the Id field
 * as type parameters.
 */
@Repository
public interface PrototypeRepository extends JpaRepository<PrototypeEntity, Integer> {
}
