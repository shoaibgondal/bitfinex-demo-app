package com.example.bitfinex.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class TickerLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float bid;
    private float bidSize;
    private float ask;
    private float askSize;
    private float dailyChange;
    private float dailyChangePerc;
    private float lastPrice;
    private float volume;
    private float high;
    private float low;
    private Date createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }
}
