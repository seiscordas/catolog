package br.com.klsites.catalog.tests;

import br.com.klsites.catalog.dto.ProductDTO;
import br.com.klsites.catalog.entities.Category;
import br.com.klsites.catalog.entities.Product;
import br.com.klsites.catalog.entities.Role;

import java.time.Instant;

public class Factory {
     public static Product createProduct(){
         Product product =  new Product(1L, "Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.parse("2022-12-05T09:26:00Z"));
         product.getCategories().add(createCategory());
         return product;
     }
     public static ProductDTO createProductDTO(){
         Product product = createProduct();
         return new ProductDTO(product, product.getCategories());
     }
     public static Category createCategory(){
         return new Category(1L, "Electronics");
     }
}
