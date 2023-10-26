package dev.capstone.dto;

import dev.capstone.domain.enumerated.Level;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class FoodMenuSaveDTO {
    private Integer market_id;
    private String menu_type;
    private String menu_name;
    private String menu_description;
    private Float price;
    private Map<String, Object> options;
}
