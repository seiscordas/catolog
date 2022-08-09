package br.com.klsites.catalog.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {
    private Long id;
    private String name;

    public Category(){
    }
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
