package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.SubCategory;
import br.com.harbitech.school.subcategory.SubcategoryDto;

public class CourseDto {

    private Long id;
    private String name;
    private int completionTimeInHours;
    private SubcategoryDto subcategoryDto;

    public CourseDto(Long id, String name, int completionTimeInHours, SubcategoryDto subcategoryDto) {
        this.id = id;
        this.name = name;
        this.completionTimeInHours = completionTimeInHours;
        this.subcategoryDto = subcategoryDto;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCompletionTimeInHours() {
        return completionTimeInHours;
    }

    public SubcategoryDto getSubcategoryDto() {
        return subcategoryDto;
    }
}
