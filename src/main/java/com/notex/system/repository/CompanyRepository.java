package com.notex.system.repository;

import com.notex.system.models.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Optional<Company> findByIdAndStatusTrue(String id);
    List<Company> findByNameContaining(String searchTerm);
}
