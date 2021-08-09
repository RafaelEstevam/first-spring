package fatec.sp.gov.br.firstspring.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fatec.sp.gov.br.firstspring.entity.Category;
import fatec.sp.gov.br.firstspring.entity.Login;
import fatec.sp.gov.br.firstspring.entity.Profile;
import fatec.sp.gov.br.firstspring.entity.Task;
import fatec.sp.gov.br.firstspring.repository.CategoryRepository;
import fatec.sp.gov.br.firstspring.repository.LoginRepository;
import fatec.sp.gov.br.firstspring.repository.ProfileRepository;
import fatec.sp.gov.br.firstspring.repository.TaskRepository;

@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void taskRepositorySaveTaskOk(){
        Task task = new Task();
        task.setTitle("Task test");
        taskRepository.save(task);

        assertNotNull(task.getId());
    }
    
    @Test
    void TaskRepositorySaveTaskWithProfileAndCategoryOk(){

        Login login = loginRepository.findByEmail("teste@teste.com");
        Profile profile = profileRepository.findProfileByLoginEmail(login.getEmail());
        Category category = categoryRepository.findCategoryByName("front");
        
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

}
