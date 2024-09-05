package com.etiya.staj.business;

import com.etiya.staj.dataAccess.ControlRepository;
import com.etiya.staj.dataAccess.ItemRepository;
import com.etiya.staj.model.Control;
import com.etiya.staj.model.items.Info;
import com.etiya.staj.model.items.ItemDb;
import com.etiya.staj.model.items.ItemDto;
import com.etiya.staj.model.items.Item;
import com.etiya.staj.utility.mappers.DtoMapUtility;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemManager implements ItemService {
    private final EmailManager emailManager;
    private final WebClient webClient;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ControlRepository controlRepository;

    public ItemManager(EmailManager emailManager, WebClient.Builder webClientBuilder) {
        this.emailManager = emailManager;
        this.webClient = webClientBuilder.baseUrl("https://run.mocky.io/v3").build();
    }

    public List<ItemDto> getAllItemsRequest(String name, String namespace, String version){
        List<Item> items = webClient.get()
                .uri("/e7947578-4c54-4a06-a86b-f978b0b8811f")
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
        try{
            itemRepository.save(item);
        }catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<ItemDb> getAllSaved(String namespace, String date) {
        return itemRepository.findAll().stream().filter(s -> namespace == null || namespace.isEmpty() || s.getNamespace().equalsIgnoreCase(namespace))
                .filter(s -> date == null || date.isEmpty() || s.getDate().substring(0, 10).equalsIgnoreCase(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getAllItemsNotMatchingControl(String email) throws MessagingException {
        List<Item> items = webClient.get()
                .uri("/e7947578-4c54-4a06-a86b-f978b0b8811f")
                .retrieve()
                .bodyToMono(Info.class)
                .map(Info::getItems)
                .block(); //sync

        if(items == null){
            System.out.println("Items is empty!");
            return null;
        }

        List<ItemDto> dtos = DtoMapUtility.convertToDto(items);
        List<Control> controls = controlRepository.findAll();

        Map<String, String> namespaceToKeyMap = new HashMap<>();
        for (Control control : controls) {
            namespaceToKeyMap.put(control.getNamespace().toLowerCase(), control.getControlKey());
        }

        List<ItemDto> nomatch = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            String dtoNamespace = dtos.get(i).getNamespace().toLowerCase();
            String dtoVersion = dtos.get(i).getVersion().toLowerCase();

            String controlKey = namespaceToKeyMap.get(dtoNamespace);
            if (controlKey != null && !dtoVersion.startsWith(controlKey.toLowerCase())) {
                nomatch.add(dtos.get(i));
            }
        }

        if(!nomatch.isEmpty()){
            StringBuilder message = new StringBuilder();
            Set<String> uniqueNamespacesKey = new HashSet<>();

            for (ItemDto item : nomatch) {
                String trueKey = namespaceToKeyMap.get(item.getNamespace().toLowerCase());
                String identifier = item.getNamespace().toLowerCase() + "-" + trueKey;
                if(!uniqueNamespacesKey.contains(identifier)){
                    message.append("Namespace ")
                            .append(item.getNamespace())
                            .append(" için key ")
                            .append(trueKey)
                            .append(" olmalı, aşağıdakiler buna uymuyor!\n").toString();
                    uniqueNamespacesKey.add(identifier);
                }
            }
            emailManager.sendEmail(nomatch, message.toString() , email, "Items that don't match the namespace-key pair");
        }

        return nomatch;
    }
}