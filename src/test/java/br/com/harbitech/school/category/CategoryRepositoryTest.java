package br.com.harbitech.school.category;

import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.util.builder.CategoryBuilder;
import br.com.harbitech.school.util.builder.SubcategoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static br.com.harbitech.school.util.builder.CategoryBuilder.*;
import static br.com.harbitech.school.util.builder.CourseBuilder.*;
import static br.com.harbitech.school.util.builder.SubcategoryBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager em;

    @org.junit.jupiter.api.Nested
    class FindByCodeUrl {
        @Test
        void should_load_one_category_searching_by_code_url() {
            em.persist(mobileCategory(CategoryStatus.ACTIVE));

            Optional<Category> possibleCategory = categoryRepository.findByCodeUrl("mobile");

            assertTrue(possibleCategory.isPresent());
            assertEquals("mobile", possibleCategory.get().getCodeUrl());
        }

        @Test
        void should_not_load_one_category_when_doesnt_have_one_category_with_the_code_url_passed() {
            Optional<Category> possibleCategory = categoryRepository.findByCodeUrl("categoryDoesntExist");

            assertThat(possibleCategory).isEmpty();
        }
    }

    @org.junit.jupiter.api.Nested
    class FindAllByOrderByName {
        @Test
        void should_load_all_categories_orderly_by_name() {
            em.persist(mobileCategory(CategoryStatus.ACTIVE));
            em.persist(dataScienceCategory(CategoryStatus.INACTIVE));

            List<Category> categories = categoryRepository.findAllByOrderByName();

            assertThat(categories)
                    .hasSize(2)
                    .extracting(Category::getCodeUrl)
                    .containsExactly("data-science", "mobile");
        }

        @Test
        void should_not_load_any_categories_when_dont_have() {
            List<Category> categories = categoryRepository.findAllByOrderByName();

            assertThat(categories).isEmpty();
        }
    }

    @org.junit.jupiter.api.Nested
    class FindAllByStatus {
        @Test
        void should_load_all_categories_searching_by_status() {
            em.persist(mobileCategory(CategoryStatus.ACTIVE));
            em.persist(dataScienceCategory(CategoryStatus.INACTIVE));

            List<Category> categories = categoryRepository.findAllByStatus(CategoryStatus.ACTIVE);

            assertThat(categories)
                    .hasSize(1)
                    .extracting(Category::getCodeUrl)
                    .containsExactly("mobile");
        }

        @Test
        void should_not_load_categories_when_dont_have_with_the_status_passed() {
            List<Category> categories = categoryRepository.findAllByStatus(CategoryStatus.ACTIVE);

            assertThat(categories).isEmpty();
        }
    }

    @org.junit.jupiter.api.Nested
    class FindAllActiveCategoriesWithPublicCourses {
        @Test
        void should_load_all_active_categories_with_public_courses() {
            Category mobileCategory = em.persist(mobileCategory(CategoryStatus.ACTIVE));
            Subcategory androidSubcategory = em.persist(androidSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));
            em.persist(androidCourse(CourseVisibility.PUBLIC, androidSubcategory));

            List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

            assertThat(categories)
                    .hasSize(1)
                    .extracting(Category::getCodeUrl)
                    .containsExactly("mobile");
        }

        @Test
        void should_not_load_any_categories_when_the_course_is_private() {
            Category mobileCategory = em.persist(mobileCategory(CategoryStatus.ACTIVE));
            Subcategory androidSubcategory = em.persist(androidSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));
            em.persist(androidCourse(CourseVisibility.PRIVATE, androidSubcategory));

            List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

            assertThat(categories).isEmpty();
        }

        @Test
        void should_not_load_any_categories_when_the_category_is_inactive() {
            Category mobileCategory = em.persist(mobileCategory(CategoryStatus.INACTIVE));
            Subcategory androidSubcategory = em.persist(androidSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));
            em.persist(androidCourse(CourseVisibility.PUBLIC, androidSubcategory));

            List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

            assertThat(categories).isEmpty();
        }

        @Test
        void should_not_load_any_categories_when_the_subCategory_is_inactive() {
            Category mobileCategory = em.persist(mobileCategory(CategoryStatus.ACTIVE));
            Subcategory androidSubcategory = em.persist(androidSubcategory(SubCategoryStatus.INACTIVE, mobileCategory));
            em.persist(androidCourse(CourseVisibility.PUBLIC, androidSubcategory));

            List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

            assertThat(categories).isEmpty();
        }

        @Test
        void should_not_load_any_categories_when_the_category_has_not_subcategory() {
            em.persist(dataScienceCategory(CategoryStatus.ACTIVE));

            List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

            assertThat(categories).isEmpty();
        }

        @Test
        void should_not_load_any_categories_when_the_category_has_not_course() {
            Category dataScienceCategory = em.persist(dataScienceCategory(CategoryStatus.ACTIVE));
            em.persist(sqlSubcategory(SubCategoryStatus.ACTIVE, dataScienceCategory));

            List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

            assertThat(categories).isEmpty();
        }

        @Test
        void should_not_load_all_active_categories_with_public_courses_in_order_of_category() {
            Category mobileCategory = em.persist(mobileCategory(CategoryStatus.ACTIVE));
            Subcategory androidSubcategory = em.persist(androidSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));
            em.persist(androidCourse(CourseVisibility.PUBLIC, androidSubcategory));

            Category frontEndCategory = em.persist(frontEndCategory(CategoryStatus.ACTIVE));
            Subcategory htmlSubcategory = em.persist(htmlSubcategory(SubCategoryStatus.ACTIVE, frontEndCategory));
            em.persist(htmlAndCssCourse(CourseVisibility.PUBLIC, htmlSubcategory));

            List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

            assertThat(categories)
                    .hasSize(2)
                    .extracting(Category::getCodeUrl)
                    .containsExactly("mobile", "front-end");
        }

        @Test
        void should_load_active_categories_with_public_courses_in_order_of_subcategory() {
            em.persist(aCategory()
                    .withName("Programação Antiga")
                    .withCodeUrl("programacao-antiga")
                    .withStatus(CategoryStatus.INACTIVE).create());

            Category programacao = em.persist(aCategory()
                    .withName("Programação")
                    .withCodeUrl("programacao")
                    .withStatus(CategoryStatus.ACTIVE).create());

            Subcategory javaSubcategory = em.persist(new SubcategoryBuilder("sub java", "java", programacao)
                    .withStatus(SubCategoryStatus.ACTIVE).create());

            Subcategory phpSubcategory = em.persist(new SubcategoryBuilder("sub php", "php", programacao)
                    .withStatus(SubCategoryStatus.ACTIVE).create());

            programacao.setSubCategories(List.of(javaSubcategory, phpSubcategory));

            em.persist(aCourse()
                    .withVisibility(CourseVisibility.PUBLIC)
                    .withCodeUrl("curso-spring")
                    .withSubcategory(javaSubcategory)
                    .create());
            em.persist(aCourse()
                    .withVisibility(CourseVisibility.PUBLIC)
                    .withCodeUrl("cursao-laravel")
                    .withSubcategory(phpSubcategory)
                    .create());


            List<Category> categories = categoryRepository.findAllActiveCategoriesWithPublicCourses();

            assertThat(categories).hasSize(1);

            List<Subcategory> subcategories = categories.get(0).getSubCategories();

            assertThat(subcategories)
                    .hasSize(2)
                    .extracting(Subcategory::getCodeUrl)
                    .containsExactly("java", "php");
        }
    }
}
