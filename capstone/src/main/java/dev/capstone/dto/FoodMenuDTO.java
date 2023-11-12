package dev.capstone.dto;

import dev.capstone.domain.enumerated.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class FoodMenuDTO {
    private Integer market_id;
    private String menu_type;
    private String menu_name;
    private String menu_description;
    private Float price;
    private Float discount_price;
    private String options;
}
