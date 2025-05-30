package org.example.newsfeedproject.user.repository;

import org.example.newsfeedproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    // ID 값 예외처리
    default User findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"유저를 찾을 수 없습니다."));
    }

    // 이메일 검증 ( 존재하면 true, 없으면 false )
    boolean existsByEmail(String email);

    // 닉네임 검증 ( 존재하면 true, 없으면 false )
    boolean existsByNickname(String nickname);

    // 삭제된 유저까지 조회
    @Query(value = "Select * FROM user WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmailWithDeleted(@Param("email") String email);

}
