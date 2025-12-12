package com.latptop.flexuy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(nullable = false,name="name")
    private String name;
    @Column(nullable = false,name="price")
    private float price;
    @Column(name="image")
    private String image;
    @Column(name="shortDesc")
    private String shortDesc;
    @Column(name="detailDesc")
    private String detailDesc;
    @Column(nullable = false,name="quantity")
    private int quantity;
    @Column(name="sold")
    private float sold;
     @Column(name="factory")
    private String factory;
     @Column(name="target")
    private String target;
     @Column(name="avgRating")
    private float avgRating;
    @Column(name="ratingCount")
    private int ratingCount;
}
