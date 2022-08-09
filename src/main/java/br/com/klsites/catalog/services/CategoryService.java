package br.com.klsites.catalog.services;

import br.com.klsites.catalog.entities.Category;
import br.com.klsites.catalog.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CatalogRepository repository;

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return repository.findAll();
    }
}
