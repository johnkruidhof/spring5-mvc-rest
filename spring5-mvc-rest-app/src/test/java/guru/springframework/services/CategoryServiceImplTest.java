package guru.springframework.services;

import guru.springframework.api.v1.mapper.CategoryMapper;
import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

public class CategoryServiceImplTest {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategories() throws Exception {
        // given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        given(categoryRepository.findAll()).willReturn(categories);

        // when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        // then
        assertEquals(3, categoryDTOS.size());
    }

    @Test
    public void getCategoryByName() throws Exception {
        // given
        Category category = Category.builder().id(ID).name(NAME).build();
        given(categoryRepository.findCategoryByName(anyString())).willReturn(category);

        // when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        // then
        assertNotNull(categoryDTO);
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}
