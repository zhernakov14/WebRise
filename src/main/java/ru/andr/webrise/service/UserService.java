package ru.andr.webrise.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.andr.webrise.controller.dto.user.UserRequestDto;
import ru.andr.webrise.controller.dto.user.UserResponseDto;
import ru.andr.webrise.controller.dto.user.UserUpdateDto;
import ru.andr.webrise.model.User;
import ru.andr.webrise.repository.UserRepository;
import ru.andr.webrise.util.ExceptionFactory;
import ru.andr.webrise.util.UserMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void save(UserRequestDto userCreateDto) {
        User userToSave = userMapper.toEntity(userCreateDto);
        log.info("Сохранение пользователя {}", userToSave.getName());
        userRepository.save(userToSave);
    }

    public UserResponseDto getById(Long id) {
        log.info("Поиск пользователя с ID {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.createUserEntityNotFoundException(id));
        return userMapper.toDto(user);
    }

    public void update(Long id, UserUpdateDto updateDto) {
        if (!userRepository.existsById(id)) {
            throw ExceptionFactory.createUserEntityNotFoundException(id);
        }
        log.info("Обновление пользователя с ID {}", id);
        User updatedUser = userMapper.updateEntity(updateDto);
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw ExceptionFactory.createUserEntityNotFoundException(id);
        }
        log.info("Удаление пользователя с ID {}", id);
        userRepository.deleteById(id);
    }
}
