package fatec.sp.gov.br.firstspring.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fatec.sp.gov.br.firstspring.entity.Category;
import fatec.sp.gov.br.firstspring.repository.CategoryRepository;

@SpringBootTest
@Transactional
@Rollback
public class CategoryRepositoryTest {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void categoryRepositorySaveCategoryOk(){
        Category category = new Category();
        category.setName("adsf");
        categoryRepository.save(category);
        assertNotNull(category.getId());
    }

    // TODO 
    // @Test
    // void categoryRepositorySaveRepeatCategoryException(){
        
    // }

    @Test
    void categoryRepositoryGetByIdOk(){
        // Category category = categoryRepository.findCategoryByName("Front-end");
        long id = 1;
        assertNotNull(categoryRepository.getById(id));
    }

    @Test
    void categoryRepositoryGetAllOk(){
        // Category category = new Category();
        // category.setName("Front-end");
        // categoryRepository.save(category);
        assertFalse(categoryRepository.findAll().isEmpty());
    }
}
