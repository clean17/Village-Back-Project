package shop.mtcoding.village.model.reservation;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.core.jpa.BaseTime;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.ReservationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reservation_tb")
public class Reservation {

    @Comment("예약 상태")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("예약 아이디")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("예약한 유저 정보")
    private User user;

    @Comment("공간 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Place place;

    @Comment("예약 날짜")
    private LocalDateTime date;

    @Comment("예약 시작 시간")
    private LocalDateTime startTime;

    @Comment("예약 마감 시간")
    private LocalDateTime endTime;

    @Comment("예약 인원수")
    private Integer peopleNum;



    @Builder
    public Reservation(User user, Place place, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, Integer peopleNum, ReservationStatus status) {
        this.user = user;
        this.place = place;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.peopleNum = peopleNum;
        this.status = status;
    }
}
