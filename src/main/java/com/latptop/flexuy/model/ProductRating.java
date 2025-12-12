package com.latptop.flexuy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "productRatings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    private String comment;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    // ==========================
    //      RELATIONSHIPS
    // ==========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;    // người đánh giá

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;  // sản phẩm được đánh giá
}
