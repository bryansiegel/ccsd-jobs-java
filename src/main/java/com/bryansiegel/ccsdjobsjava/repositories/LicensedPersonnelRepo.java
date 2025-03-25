package com.bryansiegel.ccsdjobsjava.repositories;

import com.bryansiegel.ccsdjobsjava.models.LicensedPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicensedPersonnelRepo extends JpaRepository<LicensedPersonnel, Long> {
}
