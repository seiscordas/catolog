package br.com.klsites.catalog.dto;

import br.com.klsites.catalog.entities.Category;
import br.com.klsites.catalog.entities.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Getter
@Setter
public class ProductDTO {
    private Long id;
    @NotBlank(message = "Campo requerido")
    private String name;
    @NotBlank(message = "Campo requerido")
    private String description;
    @Positive(message = "Preço deve ser um valor positivo")
    private Double price;
    private String imgUrl;
    @PastOrPresent(message = "A data do produto não pode ser futura")
    private Instant date;

    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(){
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }
    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.date = entity.getDate();
    }
    public ProductDTO(Product entity, Set<Category> categories){
        this(entity);
        categories.forEach(category -> this.categories.add(new CategoryDTO(category)));
    }
}
