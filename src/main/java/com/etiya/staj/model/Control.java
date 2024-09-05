package com.etiya.staj.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CONTROLS")
public class Control {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "namespace")
    private String namespace;
    @Column(name = "control_key")
    private String controlKey;
}
