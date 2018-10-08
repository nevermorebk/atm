package com.homdirect.application.repository;

import com.homdirect.application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findById(Integer id);

    Account findByAccountNumber(String accountNumber);

    @Query(value = "SELECT * FROM account p where BINARY p.username = ?1", nativeQuery = true)
    Account find(String username);

    List<Account> findAll();
}
