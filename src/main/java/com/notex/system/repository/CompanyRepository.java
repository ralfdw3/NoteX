package com.notex.system.repository;

import com.notex.system.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {
    List<Company> findByNameContaining(String searchTerm);

    Optional<Company> findByCodeAndStatusTrue(String code);

    Page<Company> findAllByStatusTrue(Pageable pageable);
}
