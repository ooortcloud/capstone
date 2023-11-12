package dev.capstone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class GuestCookieDTO {
    private String token;
    private Integer gnumber;
}
