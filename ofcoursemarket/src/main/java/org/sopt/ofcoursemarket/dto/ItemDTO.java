package org.sopt.ofcoursemarket.dto;

import org.sopt.ofcoursemarket.domain.TradeType;

public record ItemDTO(
        String title,
        String description,
        Integer price,
        TradeType tradeType,
        Boolean isPriceNegotiable,
        String preferredTradingLocation,
        Long sellerId
) {}
