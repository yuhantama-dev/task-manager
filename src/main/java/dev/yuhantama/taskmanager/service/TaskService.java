package dev.yuhantama.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.yuhantama.taskmanager.Task;
import dev.yuhantama.taskmanager.repository.TaskRepository;

@Service // Tells Spring: "This is a Service Bean. Please manage it."
public class TaskService {
    private final TaskRepository taskRepository;

    // Constructor Injection (the modern, recommended way to inject dependencies)
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 1. CREATE a new task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // 2. READ all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // 3. READ a single task by ID (returns Optional to handle cases where it doesn't exist)
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // 4. UPDATE an existing task
    public Task updateTask(Long id, Task updatedTask) {
        // Find the existing task. If it exists, update its fields and save it.
        // If it doesn't exist, throw an exception (we will handle this properly in Commit 6).
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setDescription(updatedTask.getDescription());
                    existingTask.setCompleted(updatedTask.isCompleted()); // Lombok generates setCompleted()
                    return taskRepository.save(existingTask);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    // 5. DELETE a task by ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
