package com.example.bitfinex.demo.service;

import com.example.bitfinex.demo.domain.TickerLog;
import com.example.bitfinex.demo.repository.TickerLogRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TickerService {

    private static final Logger logger = LogManager.getLogger(TickerService.class);

    private TickerLogRepository tickerLogRepository;

    public TickerService(TickerLogRepository tickerLogRepository) {
        this.tickerLogRepository = tickerLogRepository;
    }

    public void saveTickerLogData(String[] values) {
        TickerLog tickerLog = new TickerLog();
        tickerLog.setBid(Float.parseFloat(values[1]));
        tickerLog.setBidSize(Float.parseFloat(values[2]));
        tickerLog.setAsk(Float.parseFloat(values[3]));
        tickerLog.setAskSize(Float.parseFloat(values[4]));
        tickerLog.setDailyChange(Float.parseFloat(values[5]));
        tickerLog.setDailyChangePerc(Float.parseFloat(values[6]));
        tickerLog.setLastPrice(Float.parseFloat(values[7]));
        tickerLog.setVolume(Float.parseFloat(values[8]));
        tickerLog.setHigh(Float.parseFloat(values[9]));
        tickerLog.setLow(Float.parseFloat(values[10]));

        tickerLogRepository.save(tickerLog);
    }
}
