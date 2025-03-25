package com.bryansiegel.ccsdjobsjava.repositories;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativePersonnelRepo extends JpaRepository<AdministrativePersonnel, Long> {
}
