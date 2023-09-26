package com.akzam.mongoDbCrud.service;

import com.akzam.mongoDbCrud.document.User;
import com.akzam.mongoDbCrud.dto.Filter;
import com.akzam.mongoDbCrud.dto.user.UserRequestDto;
import com.akzam.mongoDbCrud.dto.user.UserResponseDto;
import com.akzam.mongoDbCrud.exception.user.UserAlreadyExistsException;
import com.akzam.mongoDbCrud.exception.user.UserNotFoundException;
import com.akzam.mongoDbCrud.mapper.UserMapper;
import com.akzam.mongoDbCrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAll(Filter filter) {
        List<User> users = userRepository.findAllWithOffsetAndLimit(
                filter.offset(),
                filter.limit()
        );

        return userMapper.toDto(users);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getById(String id) {
        return userMapper.toDto(getUserById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getByPhoneNumber(String phoneNumber) {
        return userMapper.toDto(getUserByPhoneNumber(phoneNumber));
    }

    @Override
    @Transactional
    public UserResponseDto create(UserRequestDto requestDto) {
        throwIfUserExists(requestDto.phoneNumber());

        User user = userMapper.toEntity(requestDto);
        user = userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto update(String id, UserRequestDto requestDto) {
        User user = getUserById(id);

        String oldPhoneNumber = user.getPhoneNumber();
        String newPhoneNumber = requestDto.phoneNumber();

        if (!Objects.equals(oldPhoneNumber, newPhoneNumber)) {
            throwIfUserExists(newPhoneNumber);
            user.setPhoneNumber(newPhoneNumber);
        }

        user.setName(requestDto.name());
        user.setYearOfBirth(requestDto.yearOfBirth());
        user.setSecondPhoneNumber(requestDto.secondPhoneNumber());

        user = userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void deleteByPhoneNumber(String phoneNumber) {
        User user = getUserByPhoneNumber(phoneNumber);
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " does not exists"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserNotFoundException("User with " + phoneNumber + " does not exists"));
    }

    private void throwIfUserExists(String phoneNumber) {
        userRepository.findByPhoneNumber(phoneNumber)
                .ifPresent(foundUser -> {
                    throw new UserAlreadyExistsException(
                            "User with " + foundUser.getPhoneNumber() + " already exists"
                    );
                });
    }

}