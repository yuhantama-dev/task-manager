package dev.yuhantama.taskmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.yuhantama.taskmanager.Task;
import dev.yuhantama.taskmanager.service.TaskService;

@RestController              // Tells Spring: "This class handles HTTP requests and returns JSON"
@RequestMapping("/api/tasks") // All endpoints in this class will start with /api/tasks
public class TaskController {

    private final TaskService taskService;

    // Constructor Injection (Spring will inject the TaskService bean)
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 1. CREATE a new task (POST /api/tasks)
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.createTask(task);
        // HTTP 201 CREATED is the standard response for successful creation
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // 2. READ all tasks (GET /api/tasks)
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // 3. READ a single task by ID (GET /api/tasks/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        // If the task exists, return HTTP 200 with the task.
        // If not, return HTTP 404 Not Found.
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)          // If present, wrap in 200 OK
                .orElse(ResponseEntity.notFound().build()); // If empty, return 404
    }

    // 4. UPDATE a task by ID (PUT /api/tasks/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        try {
            Task updatedTask = taskService.updateTask(id, task);
            return ResponseEntity.ok(updatedTask); // HTTP 200 OK
        } catch (RuntimeException e) {
            // If the service throws "Task not found", we catch it and return 404
            return ResponseEntity.notFound().build();
        }
    }

    // 5. DELETE a task by ID (DELETE /api/tasks/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        // HTTP 204 NO CONTENT is standard for successful deletion (no body returned)
        return ResponseEntity.noContent().build();
    }

}
