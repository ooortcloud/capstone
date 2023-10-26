package dev.capstone.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class GuestCookieDTO {
    private String token;
    private Integer guest_number;
}
