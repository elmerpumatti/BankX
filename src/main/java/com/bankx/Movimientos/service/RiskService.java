package com.bankx.Movimientos.service;

import com.bankx.Movimientos.model.RiskRule;
import com.bankx.Movimientos.repository.RiskRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RiskService {
    private final RiskRuleRepository riskRepo;
    public Mono<Boolean> isAllowed(String currency, String type, BigDecimal
            amount) {
        return Mono.fromCallable(() ->
                        riskRepo.findFirstByCurrency(currency)
                                .map(RiskRule::getMaxDebitPerTx)
                                .orElse(new BigDecimal("0")))
                .subscribeOn(Schedulers.boundedElastic()) // bloqueante a elastic
                .map(max -> {
                    if ("DEBIT".equalsIgnoreCase(type)) {
                        if (amount.compareTo(max) <= 0) {
                            return amount.compareTo(max) <= 0;
                        }else{
                            System.out.println("Amount");
                        }
                    }
                    return true;
                });
    }
}
