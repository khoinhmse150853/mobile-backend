package com.prm.mobile.service.impl;

import com.prm.mobile.dto.CategoryDto;
import com.prm.mobile.entity.Category;
import com.prm.mobile.repository.CategoryRepository;
import com.prm.mobile.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper mapper;

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::mapToDTO).toList();
    }

    private CategoryDto mapToDTO(Category category) {
        return mapper.map(category, CategoryDto.class);
    }

    private Category mapToEntity(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }
}
