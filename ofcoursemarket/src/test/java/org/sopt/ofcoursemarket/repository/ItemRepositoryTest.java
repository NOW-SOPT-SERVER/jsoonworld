package org.sopt.ofcoursemarket.repository;

import org.junit.jupiter.api.Test;
import org.sopt.ofcoursemarket.domain.Item;
import org.sopt.ofcoursemarket.domain.TradeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void whenSaveItem_thenItShouldBeFound() {
        //given
        Item item = Item.builder()
                .title("세미나 멀티탭")
                .description("세미나 때 유용하게 쓰여요")
                .price(50000000.0)
                .tradeType(TradeType.SALE)
                .isPriceNegotiable(false)
                .preferredTradingLocation("건대입구")
                .build();

        //when
        itemRepository.save(item);

        //then
        Item foundItem = itemRepository.findById(item.getId()).orElse(null);
        assertNotNull(foundItem);
        assertEquals("세미나 멀티탭", foundItem.getTitle());
    }

}