package fatec.sp.gov.br.firstspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.sp.gov.br.firstspring.entity.Category;
import fatec.sp.gov.br.firstspring.entity.Login;
import fatec.sp.gov.br.firstspring.repository.CategoryRepository;
import fatec.sp.gov.br.firstspring.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/all")
    public List<Category> getAllCategories(){
        return categoryService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Category getCategoryById(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping(value="/new")
    public Category postCategory(@RequestBody Category category) {
        return categoryService.postCategory(category);
    }

    @PutMapping(value="/edit/{id}")
    public Category putCategory(@RequestBody Category category){
        return categoryService.putCategory(category);
    }

    // @PutMapping(value="/edit/{id}")
    // public String putCategory(@RequestBody Category category){
    //     categoryService.putCategory(category);
    //     return "Salvou";
    // }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Login> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
