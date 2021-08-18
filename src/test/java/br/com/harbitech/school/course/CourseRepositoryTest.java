package br.com.harbitech.school.course;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static br.com.harbitech.school.util.builder.CategoryBuilder.*;
import static br.com.harbitech.school.util.builder.CourseBuilder.*;
import static br.com.harbitech.school.util.builder.SubcategoryBuilder.androidSubcategory;
import static br.com.harbitech.school.util.builder.SubcategoryBuilder.htmlSubcategory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestEntityManager em;

    private Subcategory androidSubcategory;

    @BeforeEach
    public void setUp() {
        Category mobileCategory = em.persist(mobileCategory(CategoryStatus.ACTIVE));
        this.androidSubcategory =  em.persist(androidSubcategory(SubCategoryStatus.ACTIVE, mobileCategory));
    }

    @Test
    void should_load_one_course_searching_by_code_url() {
        em.persist(androidCourse(CourseVisibility.PUBLIC, androidSubcategory));

        Optional<Course> possibleCourse = courseRepository.findByCodeUrl("android-refinando-projeto");

        assertTrue(possibleCourse.isPresent());
        assertEquals("android-refinando-projeto", possibleCourse.get().getCodeUrl());
    }

    @Test
    void should_not_load_one_course_because_doesnt_have_one_course_with_the_code_url_passed() {
        Optional<Course> possibleCourse = courseRepository.findByCodeUrl("courseDoesntExist");

        assertTrue(possibleCourse.isEmpty());
    }

    @Test
    void should_load_the_instructor_with_more_course() {
        em.persist(androidCourse(CourseVisibility.PUBLIC, androidSubcategory));
        em.persist(androidTestsCourse(CourseVisibility.PUBLIC, androidSubcategory));

        Category frontEndCategory = em.persist(frontEndCategory(CategoryStatus.ACTIVE));
        Subcategory htmlSubcategory = em.persist(htmlSubcategory(SubCategoryStatus.ACTIVE, frontEndCategory));
        em.persist(htmlAndCssCourse(CourseVisibility.PUBLIC, htmlSubcategory));

        Optional<InstructorByCourseProjection> instructors = courseRepository.findInstructorWithGreaterNumberOfCourses();

        assertThat(instructors).get()
                .extracting(InstructorByCourseProjection::getInstructor)
                .isEqualTo("Alex Felipe");
    }

    @Test
    void should_not_load_the_instructor_with_more_course() {
        Optional<InstructorByCourseProjection> instructors = courseRepository.findInstructorWithGreaterNumberOfCourses();

        assertTrue(instructors.isEmpty());
    }

    @Test
    void should_count_courses_by_categories() {
        em.persist(androidCourse(CourseVisibility.PUBLIC, androidSubcategory));
        em.persist(androidTestsCourse(CourseVisibility.PUBLIC, androidSubcategory));

        em.persist(dataScienceCategory(CategoryStatus.ACTIVE));

        Category frontEndCategory = em.persist(frontEndCategory(CategoryStatus.ACTIVE));
        Subcategory htmlSubcategory = em.persist(htmlSubcategory(SubCategoryStatus.ACTIVE, frontEndCategory));
        em.persist(htmlAndCssCourse(CourseVisibility.PUBLIC, htmlSubcategory));

        List<CategoriesByCourseProjection> categories = courseRepository.findAllCoursesCountByCategories();

        assertThat(categories)
                .hasSize(3)
                .extracting(CategoriesByCourseProjection::getName, CategoriesByCourseProjection::getAmount)
                .containsExactly(
                    tuple("Mobile", 2L),
                    tuple("Front end", 1L),
                    tuple("Data Science", 0L)
                );
    }

    @Test
    void should_load_courses_by_subcategory() {
        em.persist(androidCourse(CourseVisibility.PUBLIC, androidSubcategory));
        em.persist(androidTestsCourse(CourseVisibility.PUBLIC, androidSubcategory));

        Pageable pageable = PageRequest.of(0, 2);

        Page<Course> courses = courseRepository.findAllBySubcategory(androidSubcategory, pageable);

        assertThat(courses)
                .hasSize(2)
                .extracting(Course::getCodeUrl)
                .contains("android-refinando-projeto","android-tdd");
    }

    @Test
    void should_load_only_one_course_by_subcategory_according_to_pagination() {
        em.persist(androidCourse(CourseVisibility.PUBLIC, androidSubcategory));
        em.persist(androidTestsCourse(CourseVisibility.PUBLIC, androidSubcategory));

        Pageable pageable = PageRequest.of(0, 1);

        Page<Course> courses = courseRepository.findAllBySubcategory(androidSubcategory, pageable);

        assertThat(courses)
                .hasSize(1)
                .extracting(Course::getCodeUrl)
                .containsExactly("android-refinando-projeto");
    }

    @Test
    void should_not_load_course_by_subcategory() {
        Pageable pageable = PageRequest.of(0, 1);

        Page<Course> courses = courseRepository.findAllBySubcategory(androidSubcategory, pageable);

        assertTrue(courses.isEmpty());
    }

}
