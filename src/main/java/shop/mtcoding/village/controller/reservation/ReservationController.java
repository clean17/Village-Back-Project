package shop.mtcoding.village.controller.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.core.firebase.FirebaseCloudMessageService;
import shop.mtcoding.village.core.firebase.RequestDTO;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;
import shop.mtcoding.village.notFoundConst.ReservationConst;
import shop.mtcoding.village.service.ReservationService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationRepository reservationRepository;

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @GetMapping
    public ResponseEntity<?> getReservation(){

        List<Reservation> allReservation = reservationRepository.findAll();

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "예약내역 조회완료", allReservation), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        Optional<Reservation> optionalUser = reservationRepository.findById(id);
        System.out.println("디버그 : " + optionalUser);

        if (optionalUser.isEmpty()) {
            throw new MyConstException(ReservationConst.notFound);
        }

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "유저 예약내역 조회완료", optionalUser.get().toDTOResponse()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(
            @Valid @RequestBody ReservationSaveRequest reservationSaveRequest, BindingResult result
            ) throws IOException {

        if (result.hasErrors()) {
            throw new Exception400(result.getAllErrors().get(0).getDefaultMessage());
        }

        var saveReservation = reservationService.예약신청(reservationSaveRequest);

        // 내 휴대폰으로 연결 했을 때 토큰
         RequestDTO requestDTO = new RequestDTO("예약신청Test", reservationSaveRequest.getUserName()+ "님이 예약신청을 했습니다", "dVimDFTAQJCHMrFDJD2W18:APA91bFef_eC8HUP_PPjtGnt3_1hJR4m-BJMDr2PSfFqA9eNtnYh4XTOqCStmPKnWgv6XDCkzur7kCrxlvghvtTPttD58zYKrz8OhkZn8Pc40vO9YCRIpJhHPaMT3wEMEkF7l7TCZkDx");

        // 안드로이드 스튜디어로 연결 했을 때 토큰
//        RequestDTO requestDTO = new RequestDTO("예약신청", reservationSaveRequest.getUserName()+ "님이 예약신청을 했습니다", "e8ayeYq0QDC_D2ph3zcgPx:APA91bGT2vs5-qFhPaylG8VZxqnJpqfXRrX2cC2OwW1aadTNpEsMYL9BMAxzFH-vcAX1WZ-COPe5qLDyaJsEpk57uy6F74QbI34DTs3gapJj1nbvNhOL0-h4RoWlXCZ6Nfo0OtncgfeB");
        firebaseCloudMessageService.sendMessageTo(
                requestDTO.getTargetToken(),
                requestDTO.getTitle(),
                requestDTO.getBody());

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "예약 신청 완료", saveReservation.toResponse()), HttpStatus.OK);
    }

}