package com.my.member.repository;

import com.my.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT * FROM member ORDER BY id", nativeQuery = true)
    List<Member> searchQuery();

    // 이름으로 검색
    @Query(value = "SELECT * FROM member WHERE name LIKE %:keyword% ORDER BY id", nativeQuery = true)
    List<Member> searchName(@Param("keyword")String keyword);
    // 주소로 검색
    @Query(value = "SELECT * FROM member WHERE address LIKE %:keyword% ORDER BY id", nativeQuery = true)
    List<Member> searchAddress(@Param("keyword")String keyword);
}
