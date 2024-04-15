package org.sopt.ofcoursemarket.controller;

import org.sopt.ofcoursemarket.domain.Item;
import org.sopt.ofcoursemarket.dto.ItemDTO;
import org.sopt.ofcoursemarket.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {
        Item item = itemService.createItem(itemDTO);
        return new ResponseEntity<>(item.getId(), HttpStatus.CREATED);
    }
}
