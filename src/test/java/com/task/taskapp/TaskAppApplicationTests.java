package com.task.taskapp;

import com.task.taskapp.model.User;
import com.task.taskapp.repository.UserRepository;
import lombok.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import org.mockito.*;

@SpringBootTest

class TaskAppApplicationTests {

    @Mock
    UserRepository userRepository;

    @Test
    public void UserRepository_findUserByUsername_returnUsername() {

    }

}
