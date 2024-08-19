package com.etiya.staj.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ITEMS")
public class ItemDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "namespace")
    private String namespace;
    @Lob
    @Column(name = "data")
    private String data;
    @Column(name = "date")
    private String date;
}