package com.leadgen.api.repository;

import com.leadgen.api.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    Optional<Lead> findLeadById(Long id);
    Lead findLeadByEmail(String email);
    List<Lead> findAll();
}