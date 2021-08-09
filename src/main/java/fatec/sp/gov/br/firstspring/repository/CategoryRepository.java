package fatec.sp.gov.br.firstspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.sp.gov.br.firstspring.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    public Category findCategoryByName(String name);

}
