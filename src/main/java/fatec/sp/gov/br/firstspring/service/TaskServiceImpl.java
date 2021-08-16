package fatec.sp.gov.br.firstspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fatec.sp.gov.br.firstspring.entity.Category;
import fatec.sp.gov.br.firstspring.entity.Profile;
import fatec.sp.gov.br.firstspring.entity.Task;
import fatec.sp.gov.br.firstspring.repository.CategoryRepository;
import fatec.sp.gov.br.firstspring.repository.ProfileRepository;
import fatec.sp.gov.br.firstspring.repository.TaskRepository;


@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<Task>();
        for(Task task: taskRepository.findAll()){
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Task getTaskById(long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            return task.get();
        }
        throw new RuntimeException("Task not found");
    }

    @Override
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public Task postTask(Task task) {

        if(Objects.nonNull(task.getCategory())){
            if(task.getCategory().getId() > 0){
                Category category = categoryRepository.getById(task.getCategory().getId());
                task.setCategory(category);
            }else{
                Category newCategory = categoryServiceImpl.postCategory(task.getCategory());
                task.setCategory(newCategory);
            }
        }

        if(Objects.nonNull(task.getProfile())){
            Profile profile = profileRepository.getById(task.getProfile().getId());
            task.setProfile(profile);
        }

        taskRepository.save(task);
        Task newTask = taskRepository.getById(task.getId());

        return newTask;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Task> getTasksByProfileId(Long id) {

        List<Task> tasks = new ArrayList<Task>();
        for(Task task: taskRepository.findByTasksProfileId(id)){
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Task> getTasksByCategoryId(Long id) {

        List<Task> tasks = new ArrayList<Task>();
        for(Task task: taskRepository.findByTasksCategoryId(id)){
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Task> searchTaskByProfileIdAndCategoryId(long profileId, Long categoryId) {
        List<Task> tasks = new ArrayList<Task>();
        for(Task task: taskRepository.findByTasksProfileIdAndCategoryId(profileId, categoryId)){
            tasks.add(task);
        }
        return tasks;
    }


}
