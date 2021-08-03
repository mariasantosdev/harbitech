package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {
    Optional<Subcategory> findByCodeUrl(String codeUrl);

    List<Subcategory> findAllByCategoryOrderByOrderVisualization(Category category);

    List<Subcategory> findAllByOrderByName();

//    Page<Subcategory> findAllByCoursesIsNotEmptyOrderByCodeUrl(Pageable pageable);

//    @Query(value = "SELECT DISTINCT subcategory.name AS subcategoryName, c.name AS categoryName FROM subcategory " +
//            "JOIN course ON course.subcategory_id = subcategory.id JOIN category c ON c.id = subcategory.category_id " +
//            "WHERE subcategory.status = 'ACTIVE' ORDER BY subcategory.order_visualization DESC", nativeQuery = true)
//    List<Subcategory> findAllByCategories();
}
