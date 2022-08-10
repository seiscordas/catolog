package br.com.klsites.catalog.services;

import br.com.klsites.catalog.dto.CategoryDTO;
import br.com.klsites.catalog.entities.Category;
import br.com.klsites.catalog.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CatalogRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> categories = repository.findAll();
        return categories.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }
}
