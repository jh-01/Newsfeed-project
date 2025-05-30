package org.example.newsfeedproject.friend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.newsfeedproject.user.entity.User;

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
    private User userId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friendId;

    public Friend(User me, User friend) {
        this.userId = me;
        this.friendId = friend;
    }
}
