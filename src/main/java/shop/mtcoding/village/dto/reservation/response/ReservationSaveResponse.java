package shop.mtcoding.village.dto.reservation.response;

import lombok.Getter;
import shop.mtcoding.village.model.user.User;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> a5386fa (알림 상황 진행중)
import shop.mtcoding.village.util.status.ReservationStatus;

=======

<<<<<<< HEAD
import javax.validation.constraints.NotBlank;
>>>>>>> cb21803 (Reservation save 완료)
=======
>>>>>>> 81f6726 (Reservation 메인 페이지 , 상세 페이지 get 완료)
import java.time.LocalDateTime;

@Getter
public class ReservationSaveResponse {

    private User user;

    private Integer peopleNum;

    private LocalDateTime date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

<<<<<<< HEAD
<<<<<<< HEAD
    private ReservationStatus status;

    public ReservationSaveResponse(User user, Integer peopleNum, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, ReservationStatus status) {
=======
    public ReservationSaveResponse(User user, Integer peopleNum, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime) {
>>>>>>> cb21803 (Reservation save 완료)
=======
    private ReservationStatus status;

    public ReservationSaveResponse(User user, Integer peopleNum, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, ReservationStatus status) {
>>>>>>> a5386fa (알림 상황 진행중)
        this.user = user;
        this.peopleNum = peopleNum;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
<<<<<<< HEAD
<<<<<<< HEAD
        this.status = status;
=======
>>>>>>> cb21803 (Reservation save 완료)
=======
        this.status = status;
>>>>>>> a5386fa (알림 상황 진행중)
    }
}
