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

import dev.yuhantama.taskmanager.dto.TaskRequestDTO;
import dev.yuhantama.taskmanager.dto.TaskResponseDTO;
import dev.yuhantama.taskmanager.service.TaskService;
import jakarta.validation.Valid;

@RestController // Tells Spring: "This class handles HTTP requests and returns JSON"
@RequestMapping("/api/tasks") // All endpoints in this class will start with /api/tasks
public class TaskController {

    private final TaskService taskService;

    // Constructor Injection (Spring will inject the TaskService bean)
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 1. CREATE a new task (POST /api/tasks)
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO dto) {
        TaskResponseDTO created = taskService.createTask(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // 2. READ all tasks (GET /api/tasks)
    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }


    // 3. READ a single task by ID (GET /api/tasks/{id})
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        TaskResponseDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    // 4. UPDATE a task by ID (PUT /api/tasks/{id})
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO dto) {
        TaskResponseDTO updated = taskService.updateTask(id, dto);
        return ResponseEntity.ok(updated);
    }

    

    // 5. DELETE a task by ID (DELETE /api/tasks/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
