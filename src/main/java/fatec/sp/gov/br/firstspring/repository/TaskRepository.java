package fatec.sp.gov.br.firstspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import fatec.sp.gov.br.firstspring.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t JOIN t.profile p WHERE p.id = :id")
    List<Task> findByTasksProfileId(@Param("id") Long id);

    @Query("SELECT t FROM Task t JOIN t.profile p JOIN t.category c WHERE p.id = :profileId AND c.id = :categoryId")
    List<Task> findByTasksProfileIdAndCategoryId(@Param("profileId") Long profileId, @Param("categoryId") Long categoryId);

}
