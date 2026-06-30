package dev.yuhantama.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.yuhantama.taskmanager.Task;

@Repository // Tells Spring: "This is a Database Access Bean. Please manage it."
public interface TaskRepository extends JpaRepository<Task, Long> {
    // That's it! No methods inside!
    // By extending JpaRepository, we automatically inherit these methods:
    // - save(Task task)
    // - findById(Long id)
    // - findAll()
    // - deleteById(Long id)
    // - count()
    // And many more!
}
