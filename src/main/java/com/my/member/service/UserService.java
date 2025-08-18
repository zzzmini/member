package com.my.member.service;

import com.my.member.dto.UserDto;
import com.my.member.entity.UserEntity;
import com.my.member.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserDto dto) {
        // dto -> Entity
        UserEntity entity = UserDto.toDto(dto);
        // 저장
        userRepository.save(entity);
    }

    public List<UserDto> findAllUser() {
        return userRepository.findAll()
                .stream()
                .map(x -> UserDto.fromEntity(x))
                .toList();
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }

    public UserDto findOneUser(String email) {
        UserEntity entity = userRepository.findById(email).orElse(null);
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }
        return UserDto.fromEntity(entity);
    }
}
