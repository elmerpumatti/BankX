package com.bankx.Movimientos.repository;

import com.bankx.Movimientos.model.Account;
import com.bankx.Movimientos.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository extends ReactiveMongoRepository<Account,String> {
    Mono<Account> findByNumber(String number);
}

