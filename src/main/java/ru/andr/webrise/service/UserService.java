package ru.andr.webrise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.andr.webrise.controller.dto.user.UserRequestDto;
import ru.andr.webrise.controller.dto.user.UserResponseDto;
import ru.andr.webrise.controller.dto.user.UserUpdateDto;
import ru.andr.webrise.model.User;
import ru.andr.webrise.repository.UserRepository;
import ru.andr.webrise.util.ExceptionFactory;
import ru.andr.webrise.util.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void save(UserRequestDto userCreateDto) {
        User userToSave = userMapper.toEntity(userCreateDto);
        userRepository.save(userToSave);
    }

    public UserResponseDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.createUserEntityNotFoundException(id));
        return userMapper.toDto(user);
    }

    public void update(Long id, UserUpdateDto updateDto) {
        if (!userRepository.existsById(id)) {
            throw ExceptionFactory.createUserEntityNotFoundException(id);
        }
        User updatedUser = userMapper.updateEntity(updateDto);
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw ExceptionFactory.createUserEntityNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
