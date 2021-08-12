package fatec.sp.gov.br.firstspring.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.sp.gov.br.firstspring.entity.Task;
import fatec.sp.gov.br.firstspring.service.TaskService;
import fatec.sp.gov.br.firstspring.view.View;

@RestController
@CrossOrigin
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @JsonView(View.Task.class)
    @GetMapping(value = "/all")
    public List<Task> getAllProfiles(){
        return taskService.getAll();
    }

    @JsonView(View.Task.class)
    @GetMapping(value = "/{id}")
    public Task getProfile(@PathVariable("id") long id){
        return taskService.getTaskById(id);
    }

    @JsonView(View.Task.class)
    @GetMapping(value = "/{id}")
    public List<Task> getTasksbyProfileId(@PathVariable("id") long id){
        return taskService.getTasksByProfileId(id);
    }
    
}
