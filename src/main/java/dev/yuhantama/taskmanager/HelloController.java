package dev.yuhantama.taskmanager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.yuhantama.taskmanager.repository.TaskRepository;

@RestController
public class HelloController {

    private final TaskRepository taskRepository;

    // Constructor Injection (Spring will give us the repository here)
    public HelloController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @GetMapping("/hello")
    public String sayHello() {
        long count = taskRepository.count(); // Asks the database: "How many tasks are there?"
        return "Spring Boot is alive! The database has " + count + " tasks right now.";
    }

}
