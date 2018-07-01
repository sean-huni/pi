package io.home.pi.task;

import io.home.pi.persistence.repo.TokenLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.task
 * USER      : sean
 * DATE      : 01-July-2018
 * TIME      : 06:25
 */
@Service
@PropertySource("classpath:application.properties")
public class TokensPurgeTask {

    private TokenLogRepo tokenRepository;

    @Autowired
    public TokensPurgeTask(TokenLogRepo tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Scheduled(cron = "${purge.cron.expression}")
    @Transactional
    public void purgeExpired() {

        Timestamp now = Timestamp.from(Instant.now());
        tokenRepository.deleteAllExpiredSince(now);
    }
}
