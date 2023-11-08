package com.task.taskapp.service;

import com.task.taskapp.enums.TaskStatus;
import com.task.taskapp.exception.InvalidTaskStatusException;
import com.task.taskapp.exception.TaskNotFoundException;
import com.task.taskapp.exception.UserNotFoundException;
import com.task.taskapp.model.Task;
import com.task.taskapp.model.User;
import com.task.taskapp.repository.TaskRepository;
import com.task.taskapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Task createNewTaskWithUsername(Task task, String username) {
        // Find the user by their username
        User user = userRepository.findUserByUsername(username);

        if (user != null) {
            // set the user for the task
            task.setUser(user);
            task.setStatus(TaskStatus.PENDING);
            task.setCompletedAt(null);

            // Set the creation timestamp.
            LocalDateTime currentDateTime = LocalDateTime.now();
            task.setCreatedAt(currentDateTime);
            return taskRepository.save(task);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Transactional
    public Task markTaskInProgress(Long taskId) {
        // Find the existing task by its ID
        Task existingTask = taskRepository.findById(taskId).orElse(null);

        if (existingTask != null) {
            if (existingTask.getStatus() == TaskStatus.PENDING) {
                // Update the task status to "IN_PROGRESS"
                existingTask.setStatus(TaskStatus.IN_PROGRESS);

                // Set the updatedAt timestamp to the current time.
                LocalDateTime currentDateTime = LocalDateTime.now();
                existingTask.setUpdatedAt(currentDateTime);

                // Save the updated task with the new status and updatedAt timestamp.
                return taskRepository.save(existingTask);
            } else {
                throw new InvalidTaskStatusException("Task is not in PENDING status");
            }
        } else {
            throw new TaskNotFoundException("Task not found");
        }
    }



    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Task findTaskById (Long id) {
        return taskRepository.getById(id);

    }
    public List<Task> getTaskByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public Task moveTaskToPending(Long id) {
        // Implement logic to move the task to pending
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setStatus(TaskStatus.PENDING);
            task.setCompletedAt(null);
            taskRepository.save(task);
        }
        return task;
    }

    public Task moveTaskToCompleted(Long id) {
        // Implement logic to move the task to done
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setStatus(TaskStatus.COMPLETED);
            LocalDateTime currentDateTime = LocalDateTime.now();
            task.setCompletedAt(currentDateTime);
            taskRepository.save(task);
        }
        return task;
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
    public Task editTask(Long id, Task updatedTask) {
        // Implement task editing logic
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            LocalDateTime currentDateTime = LocalDateTime.now();
            task.setUpdatedAt(currentDateTime);
            taskRepository.save(task);
        }
        return task;
    }
}
