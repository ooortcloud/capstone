package dev.capstone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GuestWaitingDTO {

    private Integer numberOfPeople;
    private String details;
    private Integer market_id;

}
