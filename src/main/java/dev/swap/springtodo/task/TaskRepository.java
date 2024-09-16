package dev.swap.springtodo.task;

import dev.swap.springtodo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(Long id);
    Optional<Task> findByIdAndUserId(Long taskId, Long userId);

}
