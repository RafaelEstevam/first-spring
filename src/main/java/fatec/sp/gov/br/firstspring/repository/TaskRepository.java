package fatec.sp.gov.br.firstspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import fatec.sp.gov.br.firstspring.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    //TO DO , why server only work with native query?
    //TO DO , I cant use more querys in repository, why?
    
    @Query(nativeQuery = true, value="select * from task t where t.profile_id = ?1")
    List<Task> findByTasksProfileId(String id);

    @Query(nativeQuery = true, value="select * from task t where t.profile_id IS NULL")
    List<Task> findByNull();

}
