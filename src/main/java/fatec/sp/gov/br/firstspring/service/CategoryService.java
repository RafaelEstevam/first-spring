package fatec.sp.gov.br.firstspring.service;

import java.util.List;

import fatec.sp.gov.br.firstspring.entity.Category;

public interface CategoryService {

    public List<Category> getAll();

    public Category getCategoryById(Long id);

    public Category postCategory(Category category);

    public Category putCategory(Category category);
    // public String putCategory(Category category);

    public void deleteCategoryById(Long id);

}
