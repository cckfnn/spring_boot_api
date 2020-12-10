package com.example.spring_boot_api.repositories;

import com.example.spring_boot_api.entities.HoldEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldRepository extends CrudRepository<HoldEntity, Long> {
    HoldEntity findByGUIDRequest(String GUIDRequest);
}
