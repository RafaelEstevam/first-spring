package fatec.sp.gov.br.firstspring.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<Task> getAllTasks(){
        return taskService.getAll();
    }

    @JsonView(View.Task.class)
    @GetMapping(value = "/{id}")
    public Task getTask(@PathVariable("id") long id){
        return taskService.getTaskById(id);
    }

    @JsonView(View.Task.class)
    @GetMapping(value = "/profile/{id}")
    public List<Task> getTasksbyProfile(@PathVariable("id") Long id){
        return taskService.getTasksByProfileId(id);
    }

    @JsonView(View.Task.class)
    @GetMapping(value = "/category/{id}")
    public List<Task> getTasksbyCategory(@PathVariable("id") Long id){
        return taskService.getTasksByCategoryId(id);
    }

    @JsonView(View.Task.class)
    @GetMapping(value = "/search/")
    public List<Task> getTasksbyProfileAndCategory(@PathParam("profileId") Long profileId, @PathParam("categoryId") Long categoryId){
        return taskService.searchTaskByProfileIdAndCategoryId(profileId, categoryId);
    }

    @JsonView(View.Task.class)
    @PostMapping(value="/new")
    public Task postTask(@RequestBody Task task) {
        return taskService.postTask(task);
    }

    @JsonView(View.Task.class)
    @PutMapping(value="/edit/{id}")
    public Task putTask(@RequestBody Task task){
        return taskService.postTask(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> delete(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
