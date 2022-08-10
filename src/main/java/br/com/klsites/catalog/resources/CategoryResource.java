package br.com.klsites.catalog.resources;

import br.com.klsites.catalog.dto.CategoryDTO;
import br.com.klsites.catalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    @Autowired
    private CategoryService service;
    @GetMapping
     public ResponseEntity<List<CategoryDTO>> findAll(){
         List<CategoryDTO> list = service.findAll();
         return  ResponseEntity.ok().body(list);
     }
}
