package com.etiya.staj.model.items;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {
    private String name;
    private String namespace;
    private String version;
}
