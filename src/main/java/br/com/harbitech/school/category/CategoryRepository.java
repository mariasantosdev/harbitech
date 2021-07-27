package br.com.harbitech.school.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCodeUrl(String codeUrl);

    List<Category> findAllByStatus(CategoryStatus status);
}
