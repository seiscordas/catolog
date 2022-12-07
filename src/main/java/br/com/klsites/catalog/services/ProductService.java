package br.com.klsites.catalog.services;

import br.com.klsites.catalog.dto.CategoryDTO;
import br.com.klsites.catalog.dto.ProductDTO;
import br.com.klsites.catalog.entities.Category;
import br.com.klsites.catalog.entities.Product;
import br.com.klsites.catalog.repositories.CategoryRepository;
import br.com.klsites.catalog.repositories.ProductRepository;
import br.com.klsites.catalog.services.exceptions.DatabaseException;
import br.com.klsites.catalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    //private Page<Product> product;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> product = repository.findAll(pageable);
        return product.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO ProductDTO) {
        Product entity = new Product();
        copyDtoToEntity(ProductDTO, entity);
        //entity.setName(ProductDTO.getName());
        return new ProductDTO(entity);

    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(productDTO, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
         try {
             repository.deleteById(id);
         }catch (EmptyResultDataAccessException e){
             throw new ResourceNotFoundException("Id not found " + id);
         }catch (DataIntegrityViolationException e){
             throw new DatabaseException("Integrity violation");
         }
    }
    private void copyDtoToEntity(ProductDTO dto, Product entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());

        entity.getCategories().clear();
        for(CategoryDTO categoryDTO : dto.getCategories()){
            Category category = categoryRepository.getReferenceById(categoryDTO.getId());
            entity.getCategories().add(category);
        }
    }
}
