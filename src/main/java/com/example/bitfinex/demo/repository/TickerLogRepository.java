package com.example.bitfinex.demo.repository;

import com.example.bitfinex.demo.domain.TickerLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TickerLogRepository extends JpaRepository<TickerLog, Long> {
}
