package br.com.harbitech.school.category;

import br.com.harbitech.school.subcategory.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCodeUrl(String codeUrl);

    List<Category> findAllByStatus(CategoryStatus status);

    List<Category> findAllByOrderByName();

//    @Query(value = "SELECT DISTINCT category.name AS name, category.icon_path AS icon FROM category JOIN " +
//            "subcategory ON category.id = subcategory.category_id JOIN course ON course.subcategory_id = subcategory.id " +
//            "WHERE category.status = 'ACTIVE'  AND course.visibility ='PUBLIC' ORDER BY category.order_visualization DESC",
//            nativeQuery = true)
//    List<ActiveCategoriesProjection> findAllActiveCategories();

    @Query(value = "SELECT DISTINCT c FROM Category c JOIN FETCH c.subCategories sc JOIN sc.courses co WHERE " +
            "c.status = 'ACTIVE' AND co.visibility = 'PUBLIC'" +
            "ORDER BY c.orderVisualization, sc.orderVisualization")
    List<Category> findAllCategoryBySubcategory();
}
