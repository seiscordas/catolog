package br.com.klsites.catalog.repositories;

import br.com.klsites.catalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Category, Long> {
}
