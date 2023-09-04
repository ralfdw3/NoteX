package com.notex.system.repository;

import com.notex.system.enums.CompanyStatus;
import com.notex.system.models.Company.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Page<Company> findByNameContainingIgnoreCaseOrderByCode(Pageable pageable, String searchTerm);
    Page<Company> findByNameContainingIgnoreCaseAndStatusOrderByCode(Pageable pageable, String searchTerm, CompanyStatus status);
    Optional<Company> findByCode(Long code);
    Page<Company> findAllByStatusOrderByCode(Pageable pageable, CompanyStatus status);
    Optional<Company> findByIdAndStatus(String id, CompanyStatus status);
}
