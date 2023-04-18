package shop.mtcoding.village.model.chat;


import lombok.*;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.chatRoom.ChatRoom;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "chat_tb")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("채팅 아이디")
    private Long id;

    @Comment("유저(호스트)아이디")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("채팅글")
    private String send;

    @Comment("채팅방 아이디")
    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @Comment("채팅한 시간")
    private Timestamp createdAt;

    @Builder
    public Chat(User user, String send, ChatRoom chatRoom) {
        this.user = user;
        this.send = send;
        this.chatRoom = chatRoom;
    }
}
