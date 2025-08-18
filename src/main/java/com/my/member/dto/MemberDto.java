package com.my.member.dto;

import com.my.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    @NotBlank(message = "이름은 반드시 입력하셔야 합니다.")
    private String name;
    @Range(min = 1, max = 100, message = "나이는 {min}~{max} 사이여야 합니다.")
    private int age;
    @Size(min = 3, max = 20, message = "주소가 너무 짧거나 길어요.(3자~20자)")
    private String address;
    // 엔티티를 받아서 Dto로 변환해 주는 함수
    public static MemberDto fromMemberEntity(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getAddress()
        );
    }

    // DTO를 받아서 Entity에 넣는 작업
    public static Member toDto(MemberDto dto) {
        Member member = new Member();
        member.setId(dto.getId());
        member.setName(dto.getName());
        member.setAge(dto.getAge());
        member.setAddress(dto.getAddress());
        return member;
    }
}
