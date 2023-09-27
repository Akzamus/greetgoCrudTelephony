package com.akzam.mongoDbCrud.service;

import com.akzam.mongoDbCrud.document.User;
import com.akzam.mongoDbCrud.dto.Filter;
import com.akzam.mongoDbCrud.dto.user.UserResponseDto;
import com.akzam.mongoDbCrud.exception.user.UserNotFoundException;
import com.akzam.mongoDbCrud.mapper.UserMapper;
import com.akzam.mongoDbCrud.repository.UserRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;


public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private UserService userService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @Test(description = "Test to verify getAll() method")
    public void testGetAll_ShouldReturnListOfUserResponseDto() {
        // Given
        Filter filter = new Filter(0L, 10L);
        List<User> users = new ArrayList<>();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        when(userRepository.findAllWithOffsetAndLimit(anyLong(), anyLong())).thenReturn(users);
        when(userMapper.toDto(users)).thenReturn(userResponseDtos);

        // When
        List<UserResponseDto> result = userService.getAll(filter);

        // Then
        assertEquals(userResponseDtos, result);
    }

    @Test(description = "Test to verify getById() method")
    public void testGetById_ShouldReturnUserResponseDto() {
        // Given
        String id = "6514872e2cc7aa400c86fb95";

        User user = new User(
                id,
                "John Doe",
                1990,
                "+7-(123)-456-78-90",
                "+7-(987)-654-32-10",
                LocalDateTime.now()
        );

        UserResponseDto userResponseDto = new UserResponseDto(
                id,
                "John Doe",
                1990,
                "+7-(123)-456-78-90",
                "+7-(987)-654-32-10",
                LocalDateTime.now()
        );

        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userResponseDto);

        // When
        UserResponseDto result = userService.getById(id);

        // Then
        assertEquals(userResponseDto, result);
    }

    @Test(
            description = "Test to verify getUserById() method when user does not exist",
            expectedExceptions = UserNotFoundException.class
    )
    public void testGetUserById_UserNotFoundException_ShouldThrowException() {
        // Given
        String nonExistingId = "6514872e2cc7aa400c86fb95";

        when(userRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // When and Then
        // Expecting UserNotFoundException to be thrown
        userService.getUserById(nonExistingId);
    }
}