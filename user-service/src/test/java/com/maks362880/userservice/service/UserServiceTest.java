package com.maks362880.userservice.service;

import com.maks362880.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import com.maks362880.userservice.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional // Откатывает транзакции после каждого теста
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User testUser;

    @BeforeEach
    public void setUp() {
        // Создаем тестового пользователя
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("password");
        testUser.setEmail("testuser@example.com");
        testUser.setRole("USER");
    }

    @Test
    public void testRegisterUser() {
        User registeredUser = userService.registerUser(testUser);

        assertNotNull(registeredUser.getId(), "ID пользователя не должен быть null");
        assertEquals("testuser", registeredUser.getUsername(), "Имя пользователя должно совпадать");
        assertTrue(passwordEncoder.matches("password", registeredUser.getPassword()), "Пароль должен быть зашифрован");
    }

    @Test
    public void testLoginUser() {
        userService.registerUser(testUser);
        User loggedInUser = userService.loginUser("testuser", "password");

        assertNotNull(loggedInUser, "Пользователь должен быть найден");
        assertEquals("testuser", loggedInUser.getUsername(), "Имя пользователя должно совпадать");
    }

    @Test
    public void testGetUserById() {
        User registeredUser = userService.registerUser(testUser);
        User foundUser = userService.getUserById(registeredUser.getId());

        assertEquals(registeredUser.getId(), foundUser.getId(), "ID пользователя должно совпадать");
    }

    @Test
    public void testGetAllUsers() {
        userService.registerUser(testUser);
        List<User> users = userService.getAllUsers();

        assertFalse(users.isEmpty(), "Список пользователей не должен быть пустым");
        assertEquals(1, users.size(), "Должен быть один пользователь");
    }

    @Test
    public void testUpdateUser() {
        User registeredUser = userService.registerUser(testUser);

        User updatedUser = new User();
        updatedUser.setUsername("newusername");
        updatedUser.setPassword("newpassword");
        updatedUser.setEmail("newemail@example.com");

        User result = userService.updateUser(registeredUser.getId(), updatedUser);

        assertEquals("newusername", result.getUsername(), "Имя пользователя должно быть обновлено");
        assertTrue(passwordEncoder.matches("newpassword", result.getPassword()), "Пароль должен быть обновлен");
        assertEquals("newemail@example.com", result.getEmail(), "Email должен быть обновлен");
    }

    @Test
    public void testDeleteUser() {
        User registeredUser = userService.registerUser(testUser);
        userService.deleteUser(registeredUser.getId());

        assertThrows(RuntimeException.class, () -> userService.getUserById(registeredUser.getId()), "Пользователь должен быть удален");
    }

    @Test
    public void testFindUserByEmail() {
        userService.registerUser(testUser);
        Optional<User> foundUser = userService.findUserByEmail("testuser@example.com");

        assertTrue(foundUser.isPresent(), "Пользователь должен быть найден по email");
        assertEquals("testuser@example.com", foundUser.get().getEmail(), "Email должен совпадать");
    }

    @Test
    public void testFindUserByUsername() {
        userService.registerUser(testUser);
        Optional<User> foundUser = userService.findUserByUsername("testuser");

        assertTrue(foundUser.isPresent(), "Пользователь должен быть найден по username");
        assertEquals("testuser", foundUser.get().getUsername(), "Username должен совпадать");
    }
}