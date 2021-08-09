package fatec.sp.gov.br.firstspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.sp.gov.br.firstspring.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
    public List<Task> findTasksByProfileId(Long id);

}
