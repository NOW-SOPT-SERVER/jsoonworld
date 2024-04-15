package org.sopt.ofcoursemarket.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sopt.ofcoursemarket.domain.Item;
import org.sopt.ofcoursemarket.domain.TradeType;
import org.sopt.ofcoursemarket.dto.ItemDTO;
import org.sopt.ofcoursemarket.repository.ItemRepository;
import org.sopt.ofcoursemarket.service.ItemService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    @DisplayName("상품 생성 및 저장 테스트")
    void whenCreateItem_thenSaveItem() {
        //given
        ItemDTO itemDTO = new ItemDTO(
                "도마",
                "장인 칼날의 흔적이 담겨있어요!",
                1000000.0,
                TradeType.SALE,
                false,
                "우리집 주방"
        );

        Item item = Item.builder()
                .title(itemDTO.getTitle())
                .description(itemDTO.getDescription())
                .price(itemDTO.getPrice())
                .tradeType(itemDTO.getTradeType())
                .isPriceNegotiable(itemDTO.getIsPriceNegotiable())
                .preferredTradingLocation(itemDTO.getPreferredTradingLocation())
                .build();
        given(itemRepository.save(any(Item.class))).willReturn(item);

        // when
        Item createdItem = itemService.createItem(itemDTO);

        // then
        assertThat(createdItem).isNotNull();
        assertThat(createdItem.getTitle()).isEqualTo("도마");
        verify(itemRepository).save(any(Item.class));
    }
}
