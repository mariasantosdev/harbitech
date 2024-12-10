package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.course.CourseVisibility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static br.com.harbitech.school.util.builder.CategoryBuilder.mobileCategory;
import static br.com.harbitech.school.util.builder.CourseBuilder.androidCourse;
import static br.com.harbitech.school.util.builder.SubcategoryBuilder.androidSubcategory;
import static br.com.harbitech.school.util.builder.SubcategoryBuilder.flutterSubcategory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class SubcategoryRepositoryTest {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private TestEntityManager em;

    private Category mobileCategory;

    @BeforeEach
    void setUp() {
        this.mobileCategory = em.persist(mobileCategory(CategoryStatus.ACTIVE));
    }

    @org.junit.jupiter.api.Nested
    class FindByCodeUrl {
        @Test
        void should_load_one_subcategory_searching_by_code_url() {
            em.persist(androidSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));

            Optional<Subcategory> possibleSubcategory = subcategoryRepository.findByCodeUrl("android");

            assertTrue(possibleSubcategory.isPresent());
            assertEquals("android", possibleSubcategory.get().getCodeUrl());
        }

        @Test
        void should_not_load_one_subcategory_when_doesnt_have_one_subcategory_with_the_code_url_passed() {
            Optional<Subcategory> possibleSubcategory = subcategoryRepository.findByCodeUrl("subcategoryDoesntExist");

            assertThat(possibleSubcategory).isEmpty();
        }
    }

    @org.junit.jupiter.api.Nested
    class FindAllByOrderByName {
        @Test
        void should_load_all_categories_orderly_by_name() {
            em.persist(androidSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));
            em.persist(flutterSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));

            List<Subcategory> subcategories = subcategoryRepository.findAllByOrderByName();

            assertThat(subcategories)
                    .hasSize(2)
                    .extracting(Subcategory::getName)
                    .containsExactly("Android", "Flutter");
        }
    }

    @org.junit.jupiter.api.Nested
    class FindAllByCategoryOrderByOrderVisualization {
        @Test
        void should_not_load_any_subcategory_when_doesnt_have_one_category_found() {
            List<Subcategory> subcategories = subcategoryRepository
                    .findAllByCategoryOrderByOrderVisualization(mobileCategory);

            assertThat(subcategories).isEmpty();
        }

        @Test
        void should_load_all_categories_orderly_by_order_visualization() {
            em.persist(androidSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));
            em.persist(flutterSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));

            List<Subcategory> subcategories = subcategoryRepository.findAllByCategoryOrderByOrderVisualization(mobileCategory);

            assertThat(subcategories)
                    .hasSize(2)
                    .extracting(Subcategory::getCodeUrl)
                    .containsExactly("android", "flutter");
        }
    }

    @org.junit.jupiter.api.Nested
    class FindAllActiveSubcategories {
        @Test
        void should_load_all_active_subcategories_with_public_courses() {
            Subcategory androidSubcategory = em.persist(androidSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));
            em.persist(androidCourse(CourseVisibility.PUBLIC, androidSubcategory));

            List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(mobileCategory);

            assertThat(subcategories)
                    .hasSize(1)
                    .extracting(Subcategory::getCodeUrl)
                    .containsExactly("android");
        }

        @Test
        void should_not_load_any_subcategories_when_the_course_is_private() {
            Subcategory androidSubcategory = em.persist(androidSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));
            androidCourse(CourseVisibility.PRIVATE, androidSubcategory);

            List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(mobileCategory);

            assertThat(subcategories).isEmpty();
        }

        @Test
        void should_not_load_any_subcategories_when_the_subcategory_has_not_course() {
            flutterSubcategory(SubCategoryStatus.ACTIVE, mobileCategory);

            List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(mobileCategory);

            assertThat(subcategories).isEmpty();
        }

        @Test
        void should_not_load_any_subcategories_when_the_subcategory_is_inactive() {
            Subcategory androidSubcategory = em.persist(androidSubcategory(SubCategoryStatus.INACTIVE, mobileCategory));
            androidCourse(CourseVisibility.PUBLIC, androidSubcategory);

            List<Subcategory> subcategories = subcategoryRepository.findAllActiveSubcategories(mobileCategory);

            assertThat(subcategories).isEmpty();
        }
    }
}
