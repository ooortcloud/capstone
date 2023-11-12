package dev.capstone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeMainUserDTO {
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_residence;
}
