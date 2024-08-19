package com.etiya.staj.business;

import com.etiya.staj.dataAccess.ItemRepository;
import com.etiya.staj.model.Info;
import com.etiya.staj.model.ItemDb;
import com.etiya.staj.model.ItemDto;
import com.etiya.staj.model.Item;
import com.etiya.staj.utility.DtoMapUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemManager implements ItemService {
    private final WebClient webClient;
    @Autowired
    private ItemRepository repository;

    public ItemManager(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://run.mocky.io/v3").build();
    }

    public List<ItemDto> getAllItemsRequest(String name, String namespace, String version){
        List<Item> items = webClient.get()
                .uri("/e648f184-aeed-40c9-9947-a8007128ee7c")
                .retrieve()
                .bodyToMono(Info.class)
                .map(Info::getItems)
                .block(); //sync

        if(items == null){
            System.out.println("Items is empty!");
            return null;
        }

        List<ItemDto> dtos = DtoMapUtility.convertToDto(items);

        return dtos.stream()
                .filter(s -> name == null || s.getName().equalsIgnoreCase(name))
                .filter(s -> namespace == null || s.getNamespace().equalsIgnoreCase(namespace))
                .filter(s -> version == null || s.getVersion().equalsIgnoreCase(version))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDb addAllItems(ItemDb item) {
//        Date now = new Date();
//        String date = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm", Locale.ENGLISH).format(now);
//
//        for (int i = 0; i < items.size(); i++) {
//            items.get(i).setDate(date);
//        }

        try{
            repository.save(item);
        }catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }
}