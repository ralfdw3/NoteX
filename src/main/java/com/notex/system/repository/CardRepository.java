package com.notex.system.repository;

import com.notex.system.enums.Status;
import com.notex.system.models.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {
    List<Card> findAllByStatusOrStatus(Status status1, Status status2);
    Page<Card> findAllByCompanyId(Pageable pageable, String id);
}
