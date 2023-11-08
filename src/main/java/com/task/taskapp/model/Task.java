package com.task.taskapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task.taskapp.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import static jakarta.persistence.GenerationType.IDENTITY;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "username")
    @JsonIgnore
    private User user;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime completedAt;
}
