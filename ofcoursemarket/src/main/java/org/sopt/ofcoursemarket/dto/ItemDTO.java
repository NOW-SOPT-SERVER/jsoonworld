package org.sopt.ofcoursemarket.dto;

import org.sopt.ofcoursemarket.domain.TradeType;
import org.sopt.ofcoursemarket.domain.TradingLocation;

public record ItemDTO(
        String title,
        String description,
        Integer price,
        TradeType tradeType,
        Boolean isPriceNegotiable,
        TradingLocation tradingLocation,
        Long sellerId
) {}
