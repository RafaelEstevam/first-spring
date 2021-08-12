package fatec.sp.gov.br.firstspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.sp.gov.br.firstspring.entity.Task;
import fatec.sp.gov.br.firstspring.repository.TaskRepository;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<Task>();
        for(Task task: taskRepository.findAll()){
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public Task getTaskById(long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            return task.get();
        }
        throw new RuntimeException("Task not found");
    }

    @Override
    public Task postTask(Task profile) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Task putTask(Task profile) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Task> getTasksByProfileId(long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
