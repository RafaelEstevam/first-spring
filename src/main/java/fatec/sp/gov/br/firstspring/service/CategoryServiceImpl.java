package fatec.sp.gov.br.firstspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.sp.gov.br.firstspring.entity.Category;
import fatec.sp.gov.br.firstspring.repository.CategoryRepository;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<Category>();
        for(Category category: categoryRepository.findAll()){
            categories.add(category);
        }
        return categories;
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }
        throw new RuntimeException("Category not found");
    }

    @Override
    public Category postCategory(Category category) {

        category.setName(category.getName());
        categoryRepository.save(category);
        Category newCategory = categoryRepository.getById(category.getId());

        if(newCategory != null ){
            return newCategory;
        }

        throw new RuntimeException("Category can't be save");
    }

    @Override
    public Category putCategory(Category newCategory) {

        //TODO Why return exception when return the updated category?
        Category category = categoryRepository.getById(newCategory.getId());
        
        category.setId(newCategory.getId());
        category.setName(newCategory.getName());

        try{
            categoryRepository.save(category);
            return newCategory;
        }catch(Exception e){
            throw new RuntimeException("User can't be save");
        }
        
    }

}
