package shop.mtcoding.village.service;


<<<<<<< HEAD
<<<<<<< HEAD
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.model.notice.NoticeRepository;
=======
=======
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
>>>>>>> 0a7ac65 (fcm 완료)
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
<<<<<<< HEAD
>>>>>>> cb21803 (Reservation save 완료)
=======
import shop.mtcoding.village.model.notice.NoticeRepository;
>>>>>>> 81f6726 (Reservation 메인 페이지 , 상세 페이지 get 완료)
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation 예약신청(ReservationSaveRequest reservationSaveRequest) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

        try {
            return reservationRepository.save(reservationSaveRequest.toEntity());
        } catch (Exception500 e) {
            throw new Exception500("로그인 오류" + e.getMessage());
        }
    }


=======
=======


>>>>>>> 81f6726 (Reservation 메인 페이지 , 상세 페이지 get 완료)
=======
>>>>>>> 0a7ac65 (fcm 완료)
        return reservationRepository.save(reservationSaveRequest.toEntity());
    }
>>>>>>> cb21803 (Reservation save 완료)
}
