package br.com.harbitech.school.subcategory;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryConverter implements Converter<String, Subcategory> {
    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryConverter(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public Subcategory convert(String id) {
        return subcategoryRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
    }
}
