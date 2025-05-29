package org.example.newsfeedproject.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.newsfeedproject.common.entity.BaseTimeEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(unique = true)
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "유효한 이메일 형식이 아닙니다."
    )
    private String email;

    @Column(unique = true)
    @Size(max = 5)
    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password;

    @Column
    private boolean is_deleted;

    // 정보를 모두 포함한 생성자
    public User(Long id, String email, String nickname, String password, boolean is_deleted) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.is_deleted = is_deleted;
    }

    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public User(Long userId) {
        super();
    }

    public void modifyProfile(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}

