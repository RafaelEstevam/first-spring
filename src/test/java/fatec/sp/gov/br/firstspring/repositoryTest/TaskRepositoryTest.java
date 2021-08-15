package fatec.sp.gov.br.firstspring.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fatec.sp.gov.br.firstspring.entity.Category;
import fatec.sp.gov.br.firstspring.entity.Login;
import fatec.sp.gov.br.firstspring.entity.Profile;
import fatec.sp.gov.br.firstspring.entity.Task;
import fatec.sp.gov.br.firstspring.repository.CategoryRepository;
import fatec.sp.gov.br.firstspring.repository.LoginRepository;
import fatec.sp.gov.br.firstspring.repository.ProfileRepository;
import fatec.sp.gov.br.firstspring.repository.TaskRepository;
import fatec.sp.gov.br.firstspring.service.TaskService;

@SpringBootTest
@Transactional
@Rollback
public class TaskRepositoryTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void taskRepositoryGetAllOk(){
        assertNotNull(taskRepository.findAll());
    }

    @Test
    void taskRepositoryGetOk(){
        long id = 1;
        assertNotNull(taskRepository.getById(id));
    }

    @Test
    void taskRepositoryGetByProfileOk(){
        long id = 1;
        assertNotNull(taskRepository.findByTasksProfileId(id));
    }

    @Test
    void taskRepositoryGetByCategoryOk(){
        long id = 1;
        assertNotNull(taskRepository.findByTasksCategoryId(id));
    }

    @Test
    void taskRepositorySaveTaskOk(){

        Task task = new Task();
        task.setTitle("Task test");
        taskRepository.save(task);

        assertNotNull(task.getId());
    }
    
    @Test
    void TaskRepositorySaveTaskWithProfileAndCategoryOk(){

        Login login = loginRepository.findByEmail("admin");
        Profile profile = profileRepository.findProfileByLoginEmail(login.getEmail());
        Category category = categoryRepository.findCategoryByName("Front-end");

        Task task = new Task();
        task.setTitle("Task test");
        task.setCategory(category);
        task.setProfile(profile);
        task.setDescription("Test description");
        task.setStatus("In progress");
        task.setProgress(0);
        task.setDeadline(new Date(1220227200L * 1000));

        taskRepository.save(task);

        assertNotNull(task.getId());

    }
    
    @Test
    void TaskRepositoryDeleteTaskThatExistsOk(){
        long id = 1;
        taskRepository.deleteById(id);
    }

}
