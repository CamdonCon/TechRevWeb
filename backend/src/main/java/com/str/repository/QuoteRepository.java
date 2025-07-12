package com.str.repository;

import com.str.model.QuoteRequest;
import com.str.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuoteRepository extends JpaRepository<QuoteRequest, Long> {
    List<QuoteRequest> findByUser(User user);
}