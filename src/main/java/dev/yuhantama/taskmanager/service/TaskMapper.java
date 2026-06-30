package dev.yuhantama.taskmanager.service;

import dev.yuhantama.taskmanager.Task;
import dev.yuhantama.taskmanager.dto.TaskRequestDTO;
import dev.yuhantama.taskmanager.dto.TaskResponseDTO;

public class TaskMapper {
     // Convert Request DTO to Entity (for CREATE)
    public static Task toEntity(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        // Note: We DO NOT set 'id' or 'createdAt' here. 
        // The database auto-generates 'id', and @PrePersist handles 'createdAt'.
        return task;
    }

    // Convert Entity to Response DTO (for GET / PUT responses)
    public static TaskResponseDTO toResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getCreatedAt()
        );
    }
}
