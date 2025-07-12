package com.str.repository;

import com.str.model.QuoteRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<QuoteRequest, Long> {

}