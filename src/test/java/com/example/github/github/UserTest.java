package com.example.github.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.github.github.Dao.UserRepository;
import com.example.github.github.Models.User;
import com.example.github.github.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User("JohnDoe", "password123");
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals("JohnDoe", createdUser.getName());
        assertEquals("password123", createdUser.getPassword());
    }

    @Test
    void testGetUserById() {
        User user = new User("JaneDoe", "password456");
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(1L);

        assertEquals(true, foundUser.isPresent());
        assertEquals("JaneDoe", foundUser.get().getName());
        assertEquals("password456", foundUser.get().getPassword());
    }
}