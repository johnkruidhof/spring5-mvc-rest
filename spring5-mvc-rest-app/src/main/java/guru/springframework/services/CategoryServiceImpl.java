package guru.springframework.services;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findCategoryByName(name);

        if (category == null)
            throw new ResourceNotFoundException("Category "+name+" not found");

        return categoryMapper.categoryToCategoryDTO(category);
    }
}