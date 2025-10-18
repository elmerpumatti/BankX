package com.bankx.Movimientos.repository;

import com.bankx.Movimientos.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
    Flux<Transaction> findByAccountIdOrderByTimestampDesc(String accountId);
}
