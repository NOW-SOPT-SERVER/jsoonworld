package org.sopt.ofcoursemarket.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.ofcoursemarket.domain.Item;
import org.sopt.ofcoursemarket.domain.TradeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("아이템 저장 후 찾기")
    void whenSaveItem_thenItShouldBeFound() {
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
        Item savedItem = itemRepository.save(item);

        //then
        assertThat(itemRepository.findById(savedItem.getId())).isPresent()
                .hasValueSatisfying(i -> {
                    assertThat(i.getTitle()).isEqualTo("세미나 멀티탭");
                    assertThat(i.getDescription()).isEqualTo("세미나 때 유용하게 쓰여요");
                });
    }

}