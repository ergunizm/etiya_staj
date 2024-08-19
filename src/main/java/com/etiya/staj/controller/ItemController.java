package com.etiya.staj.controller;

import com.etiya.staj.business.ItemService;
import com.etiya.staj.model.ItemDb;
import com.etiya.staj.model.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
    @Autowired
    private ItemService service;

    @GetMapping()
    public List<ItemDto> getAll(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String namespace,
                                @RequestParam(required = false) String version){
        return service.getAllItemsRequest(name, namespace, version);
    }

    @PostMapping()
    public ItemDb saveAll(@RequestBody ItemDb item){
        return service.addAllItems(item);
    }
}