package dev.swap.springtodo;

import com.github.javafaker.Faker;
import dev.swap.springtodo.task.Status;
import dev.swap.springtodo.task.Task;
import dev.swap.springtodo.task.TaskRepository;
import dev.swap.springtodo.user.User;
import dev.swap.springtodo.user.UserRepository;
import dev.swap.springtodo.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            TaskRepository taskRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
            ) {
        Faker faker = new Faker();
        return args -> {
            for(int i = 0; i < 10; i++) {
                User user = new User();
                user.setFirstName(faker.name().firstName());
                user.setLastName(faker.name().lastName());
                user.setEmail(faker.internet().safeEmailAddress());
                user.setPassword(passwordEncoder.encode("mine123@#"));
                List<Task> tasks = new ArrayList<>();
                for (int j = 0; j < 5; j++) {
                    Task task = Task.builder()
                            .title(faker.lorem().sentence())
                            .description(faker.lorem().paragraph())
                            .status(Status.TODO)
                            .user(user)
                            .build();
                    tasks.add(task);
                }
                user.setTasks(tasks);
                userRepository.save(user);
                taskRepository.saveAll(tasks);
            }
        };
    }
}
