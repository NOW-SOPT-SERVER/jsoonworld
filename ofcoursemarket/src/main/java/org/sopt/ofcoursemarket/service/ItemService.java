package org.sopt.ofcoursemarket.service;

import org.sopt.ofcoursemarket.domain.Item;
import org.sopt.ofcoursemarket.dto.ItemDTO;
import org.sopt.ofcoursemarket.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Item createItem(ItemDTO itemDTO) {
        Item item = Item.builder()
                .title(itemDTO.getTitle())
                .description(itemDTO.getDescription())
                .price(itemDTO.getPrice())
                .tradeType(itemDTO.getTradeType())
                .isPriceNegotiable(itemDTO.getIsPriceNegotiable())
                .preferredTradingLocation(itemDTO.getPreferredTradingLocation())
                .build();
        return itemRepository.save(item);
    }
}
