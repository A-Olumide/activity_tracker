package com.task.taskapp.repository;

import com.task.taskapp.enums.TaskStatus;
import com.task.taskapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    // public Task findByTask (String task);
    List<Task> findByStatus (TaskStatus taskStatus);

     List<Task> findAll();
     Task getById(Long id);


}
