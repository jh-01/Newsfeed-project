package org.example.newsfeedproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member userId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Member friendId;

    public Friend(Member me, Member friend) {
    }
}
