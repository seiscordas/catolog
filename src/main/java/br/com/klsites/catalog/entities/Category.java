package br.com.klsites.catalog.entities;

import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Category(){
    }
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
