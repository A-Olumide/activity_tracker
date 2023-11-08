package com.task.taskapp.service;

import com.task.taskapp.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User save(User user);

    User findByUsername(String username);
}
