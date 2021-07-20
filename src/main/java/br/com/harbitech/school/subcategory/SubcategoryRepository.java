package br.com.harbitech.school.subcategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {
    Optional<Subcategory> findByStatus(Subcategory status);
}
