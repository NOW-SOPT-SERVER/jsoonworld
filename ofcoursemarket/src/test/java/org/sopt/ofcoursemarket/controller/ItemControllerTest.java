package org.sopt.ofcoursemarket.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.ofcoursemarket.domain.Item;
import org.sopt.ofcoursemarket.domain.TradeType;
import org.sopt.ofcoursemarket.dto.ItemDTO;
import org.sopt.ofcoursemarket.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    @DisplayName("아이템 생성 API 테스트")
    void whenPostItem_thenCreateItem() throws  Exception {
        //given
        ItemDTO itemDTO = new ItemDTO(
                "텀블러",
                "엄청난 힘이 담겨있어요.",
                1000000.0,
                TradeType.SALE,
                false,
                "내 가방"
        );
        Item item = Item.builder()
                .title("텀블러")
                .description("엄청난 힘이 담겨있어요.")
                .price(1000000.0)
                .tradeType(TradeType.SALE)
                .isPriceNegotiable(false)
                .preferredTradingLocation("내 가방")
                .build();
        given(itemService.createItem(any(ItemDTO.class))).willReturn(item);

        // When & Then
        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"텀블러\",\"description\":\"엄청난 힘이 담겨있어요.\",\"price\":1000000.0,\"tradeType\":\"SALE\",\"isPriceNegotiable\":false,\"preferredTradingLocation\":\"내 가방\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(String.valueOf(item.getId())));

        // Verify results
        assertThat(itemService.createItem(itemDTO)).isEqualTo(item);
    }
}