package com.revature.WebApp.repositories;

import com.revature.WebApp.entities.SettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CRUD access to the settings database table
 */
@Repository
public interface SettingRepository  extends JpaRepository<SettingsEntity, Integer> {

}
