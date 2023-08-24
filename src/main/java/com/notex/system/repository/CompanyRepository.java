package com.notex.system.repository;

import com.notex.system.enums.CompanyStatus;
import com.notex.system.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Page<Company> findByNameContainingIgnoreCase(Pageable pageable, String searchTerm);
    Page<Company> findByNameContainingIgnoreCaseAndStatus(Pageable pageable, String searchTerm, CompanyStatus status);
    Optional<Company> findByCode(String code);
    Page<Company> findAllByStatus(Pageable pageable, CompanyStatus status);
    Optional<Company> findByIdAndStatus(String id, CompanyStatus status);
}
