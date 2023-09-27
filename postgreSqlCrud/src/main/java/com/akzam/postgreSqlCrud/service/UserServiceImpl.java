package com.akzam.postgreSqlCrud.service;

import com.akzam.postgreSqlCrud.dto.Filter;
import com.akzam.postgreSqlCrud.dto.user.UserRequestDto;
import com.akzam.postgreSqlCrud.dto.user.UserResponseDto;
import com.akzam.postgreSqlCrud.entity.User;
import com.akzam.postgreSqlCrud.exception.user.UserAlreadyExistsException;
import com.akzam.postgreSqlCrud.exception.user.UserNotFoundException;
import com.akzam.postgreSqlCrud.mapper.UserMapper;
import com.akzam.postgreSqlCrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
    public UserResponseDto getById(UUID id) {
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
    public UserResponseDto update(UUID id, UserRequestDto requestDto) {
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
    public void deleteById(UUID id) {
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
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " does not exists"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new UserNotFoundException(
                            "User with phone number " + phoneNumber + " does not exists"
                        )
                );
    }

    private void throwIfUserExists(String phoneNumber) {
        userRepository.findByPhoneNumber(phoneNumber)
                .ifPresent(foundUser -> {
                    throw new UserAlreadyExistsException(
                            "User with phone number " + foundUser.getPhoneNumber() + " already exists"
                    );
                });
    }

}