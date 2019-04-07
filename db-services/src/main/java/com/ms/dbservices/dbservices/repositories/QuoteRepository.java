package com.ms.dbservices.dbservices.repositories;

import com.ms.dbservices.dbservices.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote,Integer> {
    List<Quote> getQuotesByUsername(String username);
}
