package com.bankx.Movimientos.seed;

import com.bankx.Movimientos.model.Account;
import com.bankx.Movimientos.model.RiskRule;
import com.bankx.Movimientos.repository.AccountRepository;
import com.bankx.Movimientos.repository.RiskRuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
@Component
@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RiskRuleRepository riskRepo;

    private final AccountRepository accountRepo;

    @Override
    public void run(String... args) {
// Bloqueante (JPA)
        riskRepo.save(RiskRule.builder().currency("PEN").maxDebitPerTx(new
                BigDecimal("1500")).build());
        riskRepo.save(RiskRule.builder().currency("USD").maxDebitPerTx(new
                BigDecimal("500")).build());
// Reactivo (Mongo)
        accountRepo.deleteAll()
                .thenMany(Flux.just(
                        Account.builder().number("001-0001").holderName("Ana Peru").currency("PEN").balance(new BigDecimal("2000")).build(),
                                        Account.builder().number("001-0002").holderName("Luis Acuña").currency("PEN").balance(new BigDecimal("800")).build()
                                        ))
                                .flatMap(accountRepo::save)
                                .blockLast(); // solo para seed en arranque
    }
}
