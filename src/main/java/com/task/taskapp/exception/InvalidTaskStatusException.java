package com.task.taskapp.exception;

public class InvalidTaskStatusException extends RuntimeException{
    public InvalidTaskStatusException(String message) {
        super(message);
    }
}
