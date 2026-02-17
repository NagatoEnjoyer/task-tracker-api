package com.example.demo; // Keep your package name

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Tells Spring this handles web requests and returns raw data (JSON)
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/tasks") // Sets the base URL for this whole file
public class TaskController {

    // This is where we bring in the Repository we just made
    private final TaskRepository taskRepository;

    // Constructor Injection (This is how Spring Boot links the files together)
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // --- 1. READ: Get all tasks ---
    // Triggered when someone visits: GET http://localhost:8080/api/tasks
    @GetMapping
    public List<Task> getAllTasks() {
        // We ask the repository to find everything. Spring Boot writes the SQL!
        return taskRepository.findAll();
    }

    // --- 2. CREATE: Add a new task ---
    // Triggered when someone sends data to: POST http://localhost:8080/api/tasks
    @PostMapping
    public Task createTask(@RequestBody Task newTask) {
        // The @RequestBody takes the incoming JSON and turns it into a Java Task object.
        // Then, we save it to the database.
        return taskRepository.save(newTask);
    }

    // --- 3. UPDATE: specific task by ID ---
    // Triggered by: PUT http://localhost:8080/api/tasks/1
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        // First, we find the task we want to change
        return taskRepository.findById(id)
                .map(task -> {
                    // Update the fields with new data
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setStatus(updatedTask.getStatus());
                    // Save the changes back to the database
                    return taskRepository.save(task);
                })
                .orElseGet(() -> {
                    // If the task doesn't exist, we just create it as a new one
                    updatedTask.setId(id);
                    return taskRepository.save(updatedTask);
                });
    }

    // --- 4. DELETE: specific task by ID ---
    // Triggered by: DELETE http://localhost:8080/api/tasks/1
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}