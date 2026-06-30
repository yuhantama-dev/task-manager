package dev.yuhantama.taskmanager;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Tells Spring: "This class is a table in the database"
@Table(name = "tasks") // Optional: Explicitly names the table. If omitted, it defaults to "task"
@Data // Lombok magic: Generates Getters, Setters, toString, equals, and hashCode
@NoArgsConstructor // Generates an empty constructor (required by JPA)
@AllArgsConstructor // Generates a constructor with all fields (useful for creating objects)
public class Task {
    @Id // Marks this as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment (1, 2, 3...)
    private Long id;

    @Column(nullable = false) // Cannot be null. This column must have a value.
    private String title;

    @Column(length = 2000) // Optional: we can limit the length of the description
    private String description;

    @Column(name = "is_completed") // Renames the column in the DB to "is_completed"
    private boolean isCompleted = false; // Default value: not completed

    @Column(name = "created_at", updatable = false) // 'updatable = false' means this never changes once set
    private LocalDateTime createdAt;

    // This method runs automatically RIGHT BEFORE the object is saved to the database for the first time.
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
