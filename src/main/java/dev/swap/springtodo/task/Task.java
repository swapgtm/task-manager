package dev.swap.springtodo.task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.swap.springtodo.model.BaseEntity;
import dev.swap.springtodo.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Task extends BaseEntity {


    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.TODO;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonBackReference
    @JsonIgnoreProperties("tasks")
    private User user;

    

}
