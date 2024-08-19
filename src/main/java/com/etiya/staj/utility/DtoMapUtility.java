package com.etiya.staj.utility;

import com.etiya.staj.model.ItemDto;
import com.etiya.staj.model.Item;

import java.util.ArrayList;
import java.util.List;

public class DtoMapUtility {
    public static List<ItemDto> convertToDto(List<Item> items){
        List<ItemDto> dtos = new ArrayList<>();
        for (int i = 0; i < items.size(); i++){
            String name = items.get(i).getSpec().getName();
            String namespace = items.get(i).getNamespace();

            String image = items.get(i).getSpec().getContainer().getImage();
            String version = image.substring(image.lastIndexOf('b'));   //after b

            dtos.add(new ItemDto(name, namespace, version));
        }
        return dtos;
    }
}