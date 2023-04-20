package shop.mtcoding.village.util;

import shop.mtcoding.village.model.reservation.Reservation;

import java.time.Duration;

public class TotalPrice {
    public static int calculateTotalPrice(Reservation reservation) {

<<<<<<< HEAD
<<<<<<< HEAD
        // 예약 시작 시간과 끝 시간으로 예약 기간을 계산.
        Duration duration = Duration.between(reservation.getStartTime(), reservation.getEndTime());
        long hours = duration.toHours();

        // 장소의 1시간당 가격을 조회.
        int pricePerHour = reservation.getPlace().getPricePerHour();

        // 총 가격을 계산.
=======
        // 예약 시작 시간과 끝 시간으로 예약 기간을 계산합니다.
=======
        // 예약 시작 시간과 끝 시간으로 예약 기간을 계산.
>>>>>>> 81f6726 (Reservation 메인 페이지 , 상세 페이지 get 완료)
        Duration duration = Duration.between(reservation.getStartTime(), reservation.getEndTime());
        long hours = duration.toHours();

        // 장소의 1시간당 가격을 조회.
        int pricePerHour = reservation.getPlace().getPricePerHour();

<<<<<<< HEAD
        // 총 가격을 계산합니다.
>>>>>>> cb21803 (Reservation save 완료)
=======
        // 총 가격을 계산.
>>>>>>> 81f6726 (Reservation 메인 페이지 , 상세 페이지 get 완료)
        int totalPrice = pricePerHour * (int)hours;

        return totalPrice;
    }

}
