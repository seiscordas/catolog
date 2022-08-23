package br.com.klsites.catalog.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private String imgUrl;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant date;

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(
            name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<Category> categories = new HashSet<>();

    public Product(){
    }

    public Product(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }
}
