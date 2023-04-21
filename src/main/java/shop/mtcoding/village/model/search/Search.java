package shop.mtcoding.village.model.search;

import lombok.*;
import org.hibernate.annotations.Comment;

import shop.mtcoding.village.model.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "search_tb")
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("검색 아이디")
    private Long id;

    @Comment("유저 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("검색 키워드")
    private String keyword;

    @Builder
    public Search(Long id, User user, String keyword) {
        this.id = id;
        this.user = user;
        this.keyword = keyword;
    }




}
