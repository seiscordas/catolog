package br.com.klsites.catalog.dto;

import br.com.klsites.catalog.entities.Category;
import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String name;

    public CategoryDTO(){
    }
    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
