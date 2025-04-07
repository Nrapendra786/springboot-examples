package com.nrapendra.spring_boot_rate_limiting;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
public class AgeController {

    private static final String AGE ="Age is :";

    private final Bucket bucket;

    public AgeController() {
        Bandwidth limit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    @GetMapping(value = "/api/v1/dob/{dob}")
    public ResponseEntity<String> calculateAge(@PathVariable String dob) {
        if(bucket.tryConsume(1)) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate localDateTime = LocalDate.parse(dob, formatter);
                Period period = Period.between(localDateTime, LocalDate.now());
                return new ResponseEntity<>(AGE + period.getYears(), HttpStatus.CREATED);
            } catch (Exception ex) {
                log.error(ex.getLocalizedMessage());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
    }
}
