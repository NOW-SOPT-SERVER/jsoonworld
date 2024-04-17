package org.sopt.ofcoursemarket.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.ofcoursemarket.domain.Item;
import org.sopt.ofcoursemarket.dto.ItemDTO;
import org.sopt.ofcoursemarket.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<Long> createItem(@RequestBody ItemDTO itemDTO) {
        Item item = itemService.createItem(itemDTO);
        return ResponseEntity.ok(item.getId());
    }

}
