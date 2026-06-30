package dev.yuhantama.taskmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.yuhantama.taskmanager.Task;
import dev.yuhantama.taskmanager.dto.TaskRequestDTO;
import dev.yuhantama.taskmanager.dto.TaskResponseDTO;
import dev.yuhantama.taskmanager.exception.ResourceNotFoundException;
import dev.yuhantama.taskmanager.repository.TaskRepository;

@Service // Tells Spring: "This is a Service Bean. Please manage it."
public class TaskService {
    private final TaskRepository taskRepository;

    // Constructor Injection (the modern, recommended way to inject dependencies)
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 1. CREATE (Accepts DTO, returns DTO)
    public TaskResponseDTO createTask(TaskRequestDTO dto) {
        // Convert DTO -> Entity
        Task task = TaskMapper.toEntity(dto);
        // Save to DB
        Task savedTask = taskRepository.save(task);
        // Convert Entity -> DTO and return
        return TaskMapper.toResponseDTO(savedTask);
    }

    // 2. READ all (Returns List of DTOs)
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskMapper::toResponseDTO) // Convert each Entity to DTO
                .collect(Collectors.toList());
    }

    // 3. READ single (Returns DTO)
    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return TaskMapper.toResponseDTO(task);
    }

    // 4. UPDATE (Accepts DTO, returns DTO)
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {
        // Find existing task
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        // Update ONLY the fields that are allowed to be changed
        existingTask.setTitle(dto.getTitle());
        existingTask.setDescription(dto.getDescription());
        existingTask.setCompleted(dto.isCompleted());

        // Save and return DTO
        Task updatedTask = taskRepository.save(existingTask);
        return TaskMapper.toResponseDTO(updatedTask);
    }

    // 5. DELETE (No DTO needed, just ID)
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}
