package com.etiya.staj.business;

import com.etiya.staj.model.items.ItemDb;
import com.etiya.staj.model.items.ItemDto;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    List<ItemDto> getAllItemsRequest(String name, String namespace, String version);
    ItemDb addAllItems(ItemDb item);
    List<ItemDb> getAllSaved(String namespace, String date);

    List<ItemDto> getAllItemsNotMatchingControl(String email) throws MessagingException;
}
