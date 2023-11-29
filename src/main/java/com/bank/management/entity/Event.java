package com.bank.management.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Event {

    private Long orderId;
    private String eventStatus;
    private String phase;

}