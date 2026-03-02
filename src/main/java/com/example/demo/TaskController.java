package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://task-tracker-ui-theta.vercel.app")
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @GetMapping
    public List<Task> getAllTasks(Principal principal) {
        String username = principal.getName();
        User currentUser = userRepository.findByUsername(username).orElseThrow();
        return taskRepository.findByUser(currentUser);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task, Principal principal) {
        String username = principal.getName();
        User currentUser = userRepository.findByUsername(username).orElseThrow();
        task.setUser(currentUser);
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setStatus(updatedTask.getStatus());
                    return taskRepository.save(task);
                })
                .orElseGet(() -> {
                    updatedTask.setId(id);
                    return taskRepository.save(updatedTask);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}