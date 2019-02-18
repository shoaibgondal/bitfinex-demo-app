package com.example.bitfinex.demo.scheduler;

import com.example.bitfinex.demo.websocket.client.WebsocketClientEndpoint;
import com.example.bitfinex.demo.service.TickerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.websocket.CloseReason;
import java.net.URI;

@Component
public class TickerDumpScheduler {

    private static final Logger logger = LogManager.getLogger(TickerDumpScheduler.class);

    @Value("${ws.url}")
    private String url;

    private TickerService tickerService;

    private Object waitLock = new Object();

    public TickerDumpScheduler(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @Scheduled(fixedDelayString = "${scheduler.tickerdump.cron}")
    public void newImBroadcastJob() {
        logger.debug("****************** Scheduler Ticker Dump Started *******************");

        try {

            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(
                    new URI(url));

            // add listener
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println("Incoming Message: " + message);
                    if (message.startsWith("[")) {
                        message = message.substring(1, message.length() - 1);
                        String[] values = message.split(",");
                        processTickerValues(values);
                    }
                }
            });

            // subscribe to ticker
            clientEndPoint.sendMessage("{\"event\":\"subscribe\",\"channel\":\"ticker\", \"pair\" : \"BTCUSD\"}");

            wait4TerminateSignal();

            clientEndPoint.getUserSession().close(
                    new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Job Done"));

        } catch (Exception e) {
            e.printStackTrace();
        }


        logger.debug("****************** Scheduler Ticker Dump Completed *******************");
    }

    private void wait4TerminateSignal() {
        synchronized (waitLock) {
            try {
                waitLock.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    private void processTickerValues(String[] values) {

        tickerService.saveTickerLogData(values);

        // terminate connection
        synchronized (waitLock) {
            waitLock.notify();
        }
    }
}
