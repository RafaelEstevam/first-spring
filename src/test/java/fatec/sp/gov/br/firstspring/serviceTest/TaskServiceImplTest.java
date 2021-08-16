package fatec.sp.gov.br.firstspring.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;

import fatec.sp.gov.br.firstspring.entity.Task;
import fatec.sp.gov.br.firstspring.repository.TaskRepository;
import fatec.sp.gov.br.firstspring.service.TaskService;

@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
@SpringBootTest
@Transactional
@Rollback
public class TaskServiceImplTest {
    
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void taskServiceDeleteTaskById(){

        Task task = new Task();
        task.setTitle("Deleted task");
        taskRepository.save(task);

        taskService.deleteTaskById(task.getId());
    }

    @Test
    void taskServiceGetAllOk(){
        assertNotNull(taskService.getAll());
    }

    @Test
    void taskServiceGetTaskByIdOk(){
        long id = 1;
        assertNotNull(taskService.getTaskById(id));
    }
    
    @Test
    void taskServiceGetTasksByProfileIdOk(){
        long id = 1;
        assertNotNull(taskService.getTasksByProfileId(id));
    }

    @Test
    void taskServiceGetTasksByCategoryIdOk(){
        long id = 1;
        assertNotNull(taskService.getTasksByCategoryId(id));
    }

    @Test
    void taskServiceSearchTaskByProfileIdAndCategoryIdOk(){
        long profileId = 1;
        long categoryId = 1;
        assertNotNull(taskService.searchTaskByProfileIdAndCategoryId(profileId, categoryId));
    }

    @Test
    void taskServicePostTaskOk(){

        Task task = new Task();
        task.setTitle("Task test");
        taskService.postTask(task);

        assertNotNull(task.getId());
    }

    @Test
    void taskServicePostTaskWithoutTitleError() {
        Task task = new Task();
        assertThrows(DataIntegrityViolationException.class, () -> {
            taskService.postTask(task);
        });
    }

    @Test
    void taskServicePutTaskOk(){
        long id = 1;
        Task task = taskRepository.getById(id);
        task.setTitle("Title Edited");
        
        taskService.postTask(task);

        Task updatedTask = taskRepository.getById(id);
        assertEquals("Title Edited", updatedTask.getTitle());
    }

    // @Test
    // void TaskServiceSaveTaskWithProfileAndCategoryOk(){

    //     Task task = new Task();
    //     Login login = new Login();
    //     Profile profile = new Profile();
    //     Category category = new Category();

    //     login.setEmail("novologin@login.com");
    //     login.setPassword("novologin");
    //     loginRepository.save(login);

    //     profile.setLogin(login);
    //     profile.setName("Perfil teste");
    //     profile.setDoc("234.2345.2345");
    //     profileRepository.save(profile);

    //     category.setName("Nova categoria teste");
    //     categoryRepository.save(category);

    //     task.setProfile(profile);
    //     task.setCategory(category);
    //     task.setTitle("Task test");

    //     taskService.postTask(task);
    //     assertNotNull(task.getId());

    // }

    // @Test
    // void TaskServiceCreateANewCategoryIfNotExistsWhenSaveATaskOk(){
    //     Category category = new Category();
    //     category.setName("New Category");

    //     Task task = new Task();
    //     task.setTitle("New task");
    //     task.setCategory(category);

    //     taskService.postTask(task);
    //     assertNotNull(task.getId());
    // }

    // @Test
    // void TaskServiceCreateANewCategoryIfExistsWhenSaveATaskError(){
    //     Category category = new Category();
    //     category.setName("Backend");

    //     Task task = new Task();
    //     task.setTitle("New task");
    //     task.setCategory(category);

    //     assertThrows(DataIntegrityViolationException.class, () -> {
    //         taskService.postTask(task);
    //     });
    // }

    // @Test
    // void taskServicePutCompleteTaskOk(){

    //     // long id = 1;
    //     long categoryId = 1;
    //     long profileId = 1;

    //     Category category = categoryRepository.getById(categoryId);
    //     Profile profile = profileRepository.getById(profileId);
    //     Date currentDate = new Date();

    //     Task task = new Task();
    //     task.setTitle("New Task");
    //     task.setCategory(category);
    //     task.setProfile(profile);
    //     task.setDescription("New description");
    //     task.setProgress(0);
    //     task.setStatus("In progress");
    //     task.setDeadline(currentDate);

    //     taskRepository.save(task);

    //     Task newTask = taskRepository.getById(task.getId());

    //     assertEquals("New Task", newTask.getTitle());
    //     assertEquals("Front-end", newTask.getCategory().getName());
    //     assertEquals("Teste", newTask.getProfile().getName());
    //     assertEquals("New description", newTask.getDescription());
    //     assertEquals(0, newTask.getProgress());
    //     assertEquals("In progress", newTask.getStatus());
    //     assertEquals(currentDate, newTask.getDeadline());

    //     Task updatedTask = newTask;
    //     updatedTask.setTitle("Edited task");
    //     updatedTask.setCategory(null);
    //     updatedTask.setProfile(null);
    //     updatedTask.setDescription(null);
    //     updatedTask.setProgress(null);
    //     updatedTask.setStatus(null);
    //     updatedTask.setDeadline(null);

    //     taskRepository.save(updatedTask);

    //     assertEquals("Edited task", updatedTask.getTitle());
    //     assertNull(updatedTask.getCategory());
    //     assertNull(updatedTask.getProfile());
    //     assertNull(updatedTask.getDescription());
    //     assertNull(updatedTask.getProgress());
    //     assertNull(updatedTask.getStatus());
    //     assertNull(updatedTask.getDeadline());
    // }

}
