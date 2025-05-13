package ru.andr.webrise.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.andr.webrise.controller.dto.user.UserRequestDto;
import ru.andr.webrise.controller.dto.user.UserResponseDto;
import ru.andr.webrise.controller.dto.user.UserUpdateDto;
import ru.andr.webrise.model.User;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ObjectMapper objectMapper;

    public User toEntity(UserRequestDto dto) {
        return objectMapper.convertValue(dto, User.class);
    }

    public UserResponseDto toDto(User user) {
        return objectMapper.convertValue(user, UserResponseDto.class);
    }

    public User updateEntity(UserUpdateDto dto) {
        return objectMapper.convertValue(dto, User.class);

//        try {
//            objectMapper.updateValue(user, dto);
//        } catch (JsonMappingException e) {
//            log.error("Ошибка при обновлении сущности: {}", e.getMessage(), e);
//        }
    }
}
