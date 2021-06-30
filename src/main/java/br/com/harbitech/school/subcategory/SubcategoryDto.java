package br.com.harbitech.school.subcategory;

public class SubcategoryDto {
    private Long id;
    private String name;

    public SubcategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
