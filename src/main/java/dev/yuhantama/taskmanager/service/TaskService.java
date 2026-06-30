package dev.yuhantama.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.yuhantama.taskmanager.Task;
import dev.yuhantama.taskmanager.exception.ResourceNotFoundException;
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

    // 3. READ a single task by ID (returns Optional to handle cases where it
    // doesn't exist)
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    // 4. UPDATE an existing task
    public Task updateTask(Long id, Task updatedTask) {
        // Find the existing task. If it doesn't exist, the lambda throws our custom
        // exception.
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        // If it exists, update the fields
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompleted(updatedTask.isCompleted());

        return taskRepository.save(existingTask);
    }

    // 5. DELETE a task by ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
