package com.my.member.service;

import com.my.member.dto.UserDto;
import com.my.member.entity.UserEntity;
import com.my.member.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
