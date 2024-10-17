package com.listcategory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String size;
    private Integer quantity;

    @Lob
    private byte[] image;
}

