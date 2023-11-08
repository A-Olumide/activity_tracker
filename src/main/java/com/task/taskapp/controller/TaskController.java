package com.task.taskapp.controller;

import com.task.taskapp.enums.TaskStatus;
import com.task.taskapp.exception.InvalidTaskStatusException;
import com.task.taskapp.exception.TaskNotFoundException;
import com.task.taskapp.exception.UserNotFoundException;
import com.task.taskapp.model.Task;
import com.task.taskapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createNewTask(@RequestBody Task task, @RequestParam String username) {
        Task createdTask = taskService.createNewTaskWithUsername(task, username);
        return ResponseEntity.ok(createdTask);
    }



    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @PutMapping("/markInProgress/{taskId}")
    public ResponseEntity<?> markTaskInProgress(@PathVariable Long taskId) {
        try {
            Task updatedTask = taskService.markTaskInProgress(taskId);
            return ResponseEntity.ok(updatedTask);
        } catch (InvalidTaskStatusException e) {
            return ResponseEntity.badRequest().body("Invalid task status: " + e.getMessage());
        } catch (TaskNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public Task findTask(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @GetMapping("/pending")
    public List<Task> getPendingTask() {
        return taskService.getTaskByStatus(TaskStatus.PENDING);
    }

    @GetMapping("/completed")
    public List<Task> getCompletedTask() {
        return taskService.getTaskByStatus(TaskStatus.COMPLETED);
    }

    @GetMapping("/in-progress")
    public List<Task> getInProgressTasks() {
        return taskService.getTaskByStatus(TaskStatus.IN_PROGRESS);
    }

    @PutMapping("/{id}/move-to-pending")
    public Task moveTaskToPending(@PathVariable Long id) {
        return taskService.moveTaskToPending(id);
    }

    @PutMapping("/{id}/move-to-completed")
    public Task moveTaskToCompleted(@PathVariable Long id) {
        return taskService.moveTaskToCompleted(id);
    }

    @PutMapping("/{id}")
    public Task editTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.editTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Task id) {
        taskService.deleteTask(id);
    }
}
