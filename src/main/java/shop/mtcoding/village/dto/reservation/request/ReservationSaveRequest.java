package shop.mtcoding.village.dto.reservation.request;

<<<<<<< HEAD
<<<<<<< HEAD
import lombok.*;
import shop.mtcoding.village.core.firebase.RequestDTO;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.ReservationStatus;
<<<<<<< HEAD

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
=======
import lombok.Getter;
import lombok.ToString;
=======
import lombok.*;
import shop.mtcoding.village.core.firebase.RequestDTO;
>>>>>>> 0a7ac65 (fcm 완료)
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.user.User;
=======
>>>>>>> a5386fa (알림 상황 진행중)

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
<<<<<<< HEAD
>>>>>>> cb21803 (Reservation save 완료)
=======
import java.time.LocalTime;
>>>>>>> c391c21 (Reservation Refactor 완료)

@Getter
@ToString
public class ReservationSaveRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;

    private Integer peopleNum;

<<<<<<< HEAD
<<<<<<< HEAD
    @NotNull(message = "날짜를 선택해주세요.")
    private LocalDateTime date;

    @NotNull(message = "시작 시간을 입력해주세요.")
    private LocalDateTime startTime;

    @NotNull(message = "끝 시간을 입력해주세요.")
    private LocalDateTime endTime;

    private ReservationStatus reservationStatus;

    public LocalTime getStartTime() {
        return startTime.toLocalTime();
    }

    public LocalTime getEndTime() {
        return endTime.toLocalTime();
    }


=======
//    @NotBlank(message = "날짜를 선택해주세요.")
=======
    @NotNull(message = "날짜를 선택해주세요.")
>>>>>>> c391c21 (Reservation Refactor 완료)
    private LocalDateTime date;

    @NotNull(message = "시작 시간을 입력해주세요.")
    private LocalDateTime startTime;

    @NotNull(message = "끝 시간을 입력해주세요.")
    private LocalDateTime endTime;

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> cb21803 (Reservation save 완료)
=======
=======
    private ReservationStatus reservationStatus;

<<<<<<< HEAD
>>>>>>> a5386fa (알림 상황 진행중)
=======
    public LocalTime getStartTime() {
        return startTime.toLocalTime();
    }

    public LocalTime getEndTime() {
        return endTime.toLocalTime();
    }

>>>>>>> c391c21 (Reservation Refactor 완료)

>>>>>>> 0a7ac65 (fcm 완료)
    public Reservation toEntity() {
        User user = new User();
        user.setName(userName);

        Reservation reservation = new Reservation();
        reservation.setPeopleNum(peopleNum);
        reservation.setDate(date);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
<<<<<<< HEAD
<<<<<<< HEAD
        reservation.setStatus(reservationStatus);
        return new Reservation(user, date, startTime, endTime, peopleNum, reservationStatus);
=======
        return new Reservation(user, date, startTime, endTime, peopleNum);
>>>>>>> cb21803 (Reservation save 완료)
=======
        reservation.setStatus(reservationStatus);
        return new Reservation(user, date, startTime, endTime, peopleNum, reservationStatus);
>>>>>>> a5386fa (알림 상황 진행중)
    }

}
