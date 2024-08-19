package com.etiya.staj.business;

import com.etiya.staj.model.ItemDb;
import com.etiya.staj.model.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    List<ItemDto> getAllItemsRequest(String name, String namespace, String version);
    ItemDb addAllItems(ItemDb item);
}
