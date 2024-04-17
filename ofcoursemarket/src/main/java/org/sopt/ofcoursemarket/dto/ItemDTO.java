package org.sopt.ofcoursemarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sopt.ofcoursemarket.domain.TradeType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private String title;
    private String description;
    private Integer price;
    private TradeType tradeType;
    private Boolean isPriceNegotiable;
    private String preferredTradingLocation;
}
