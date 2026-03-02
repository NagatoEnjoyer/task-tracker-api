package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask, Principal principal) {
        String username = principal.getName();
        Task existingTask = taskRepository.findById(id).orElseThrow();
        if (!existingTask.getUser().getUsername().equals(username)) {
            return ResponseEntity.status(403).build();
        }
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());

        return ResponseEntity.ok(taskRepository.save(existingTask));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        Task existingTask = taskRepository.findById(id).orElseThrow();
        if (!existingTask.getUser().getUsername().equals(username)) {
            return ResponseEntity.status(403).build();
        }
        taskRepository.delete(existingTask);
        return ResponseEntity.ok().build();
    }
}