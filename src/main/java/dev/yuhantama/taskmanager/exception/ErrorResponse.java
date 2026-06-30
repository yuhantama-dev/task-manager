package dev.yuhantama.taskmanager.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                     // Getters, Setters, toString, equals, hashCode
@NoArgsConstructor        // Empty constructor (needed for JSON serialization)
@AllArgsConstructor       // Constructor with all fields
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
