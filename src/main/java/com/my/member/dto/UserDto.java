package com.my.member.dto;

import com.my.member.entity.UserEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private String nickname;

    // Entity -> Dto 변경
    public static UserDto fromEntity(UserEntity entity) {
        return new UserDto(
                entity.getEmail(),
                entity.getPassword(),
                entity.getNickname()
        );
    }

    // Dto -> Entity 변환
    public static UserEntity toDto(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setNickname(dto.getNickname());
        return entity;
    }
}
