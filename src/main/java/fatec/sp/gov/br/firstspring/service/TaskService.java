package fatec.sp.gov.br.firstspring.service;

import java.util.List;

import fatec.sp.gov.br.firstspring.entity.Task;

public interface TaskService {

    public List<Task> getAll();

    public List<Task> getTasksByProfileId(Long id);

    public List<Task> getTasksByCategoryId(Long id);

    // public List<Task> getTasksByProfileNull();

    public Task getTaskById(long id);

    public Task postTask(Task profile);

    // public Task putTask(Task profile);

    public void deleteTaskById(Long id);

    public List<Task> searchTaskByProfileIdAndCategoryId(long profileId, Long categoryId);
    
}