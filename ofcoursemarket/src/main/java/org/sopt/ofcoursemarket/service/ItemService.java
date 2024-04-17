package org.sopt.ofcoursemarket.service;

import org.sopt.ofcoursemarket.domain.Item;
import org.sopt.ofcoursemarket.domain.Member;
import org.sopt.ofcoursemarket.dto.ItemDTO;
import org.sopt.ofcoursemarket.repository.ItemRepository;
import org.sopt.ofcoursemarket.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, MemberRepository memberRepository) {
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Item createItem(ItemDTO itemDTO) {
        Member seller = memberRepository.findById(itemDTO.sellerId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 적합하지 않은 ID 입니다."));
        Item item = Item.builder()
                .title(itemDTO.title())
                .description(itemDTO.description())
                .price(itemDTO.price())
                .tradeType(itemDTO.tradeType())
                .isPriceNegotiable(itemDTO.isPriceNegotiable())
                .preferredTradingLocation(itemDTO.preferredTradingLocation())
                .seller(seller)
                .build();
        return itemRepository.save(item);
    }
}
