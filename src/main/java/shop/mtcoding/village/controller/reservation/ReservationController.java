package shop.mtcoding.village.controller.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
=======
import org.modelmapper.ModelMapper;
>>>>>>> cb21803 (Reservation save 완료)
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.Exception400;
<<<<<<< HEAD
<<<<<<< HEAD
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.core.firebase.FirebaseCloudMessageService;
import shop.mtcoding.village.core.firebase.RequestDTO;
<<<<<<< HEAD
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;
import shop.mtcoding.village.notFoundConst.ReservationConst;
import shop.mtcoding.village.service.ReservationService;
import shop.mtcoding.village.util.DateUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
=======
=======
import shop.mtcoding.village.core.exception.MyConstException;
>>>>>>> 81f6726 (Reservation 메인 페이지 , 상세 페이지 get 완료)
=======
>>>>>>> 1df70c2 (예약 완료 시 FCM 사용전 세팅완료)
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.reservation.ReservationDTO;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;
import shop.mtcoding.village.notFoundConst.ReservationConst;
import shop.mtcoding.village.service.ReservationService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
>>>>>>> cb21803 (Reservation save 완료)

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationRepository reservationRepository;

<<<<<<< HEAD
<<<<<<< HEAD
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    private final PlaceRepository placeRepository;

    @GetMapping
    public ResponseEntity<?> getReservation(){

        List<Reservation> allReservation = reservationRepository.findAll();

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "예약내역 조회완료", allReservation), HttpStatus.OK);
=======
=======
    private final FirebaseCloudMessageService firebaseCloudMessageService;

>>>>>>> 1df70c2 (예약 완료 시 FCM 사용전 세팅완료)
    @GetMapping
    public ResponseEntity<?> getReservation(){

        List<Reservation> allReservation = reservationRepository.findAll();

        List<ReservationDTO> allReservationDTO = allReservation.stream()
                .map(reservation -> new ModelMapper().map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());

<<<<<<< HEAD
        return new ResponseEntity<>(new ResponseDTO<>(1, "예약내역 조회완료",allReservationDTO), HttpStatus.OK);
>>>>>>> cb21803 (Reservation save 완료)
=======
        return new ResponseEntity<>(new ResponseDTO<>(1, "예약내역 조회완료",allReservation), HttpStatus.OK);
>>>>>>> 81f6726 (Reservation 메인 페이지 , 상세 페이지 get 완료)
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

<<<<<<< HEAD
<<<<<<< HEAD
        Optional<Reservation> optionalUser = reservationRepository.findById(id);
        System.out.println("디버그 : " + optionalUser);

        if (optionalUser.isEmpty()) {
            throw new MyConstException(ReservationConst.notFound);
        }

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "유저 예약내역 조회완료", optionalUser.get().toDTOResponse()), HttpStatus.OK);
=======
        Optional<Reservation> userId = reservationRepository.findById(id);

        return new ResponseEntity<>(new ResponseDTO<>(1, "유저 예약내역 조회완료", userId), HttpStatus.OK);
>>>>>>> cb21803 (Reservation save 완료)
=======
        Optional<Reservation> optionalUser = reservationRepository.findById(id);
        System.out.println("디버그 : " + optionalUser);

        if (!optionalUser.isPresent()) {
            throw new MyConstException(ReservationConst.notFound);
        }

        return new ResponseEntity<>(new ResponseDTO<>(1, "유저 예약내역 조회완료", optionalUser.get().toDTOResponse()), HttpStatus.OK);
>>>>>>> 81f6726 (Reservation 메인 페이지 , 상세 페이지 get 완료)
    }

    @PostMapping
    public ResponseEntity<?> save(
<<<<<<< HEAD
            @Valid @RequestBody ReservationSaveRequest reservationSaveRequest
            ) throws IOException {



        var saveReservation = reservationService.예약신청(reservationSaveRequest);


        // 여기 1L 자리에 placeId 들어가야함
        Place byId = placeRepository.findById(1L).get();


        LocalDateTime dateTime = LocalDateTime.now();
        LocalDate date = DateUtils.fromLocalDateTime(reservationSaveRequest.getDate());
        System.out.println(date); // 예시 출력: 2023-04-25

        // 내 휴대폰으로 연결 했을 때 토큰
         RequestDTO requestDTO = new RequestDTO("Village",
                 "[예약알림]\n"+
                 reservationSaveRequest.getUserName()+ "님이 [" + byId.getTitle() + "]에 예약 신청했습니다.\n"
                         +"날짜: "+date+"\n"
                         +"일시: "+reservationSaveRequest.getStartTime()+"~"+reservationSaveRequest.getEndTime()+"\n"
                         +"인원: "+reservationSaveRequest.getPeopleNum()+"명\n",
                 "dVimDFTAQJCHMrFDJD2W18:APA91bFef_eC8HUP_PPjtGnt3_1hJR4m-BJMDr2PSfFqA9eNtnYh4XTOqCStmPKnWgv6XDCkzur7kCrxlvghvtTPttD58zYKrz8OhkZn8Pc40vO9YCRIpJhHPaMT3wEMEkF7l7TCZkDx");

        // 안드로이드 스튜디어로 연결 했을 때 토큰
//        RequestDTO requestDTO = new RequestDTO("예약신청", reservationSaveRequest.getUserName()+ "님이 예약신청을 했습니다", "e8ayeYq0QDC_D2ph3zcgPx:APA91bGT2vs5-qFhPaylG8VZxqnJpqfXRrX2cC2OwW1aadTNpEsMYL9BMAxzFH-vcAX1WZ-COPe5qLDyaJsEpk57uy6F74QbI34DTs3gapJj1nbvNhOL0-h4RoWlXCZ6Nfo0OtncgfeB");
        firebaseCloudMessageService.sendMessageTo(
                requestDTO.getTargetToken(),
                requestDTO.getTitle(),
                requestDTO.getBody());

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "예약 신청 완료", saveReservation.toResponse()), HttpStatus.OK);
    }

=======
            @Valid @RequestBody ReservationSaveRequest reservationSaveRequest, BindingResult result
            ) {

        if (result.hasErrors()) {
            throw new Exception400(result.getAllErrors().get(0).getDefaultMessage());
        }

        var saveReservation = reservationService.예약신청(reservationSaveRequest);

        return new ResponseEntity<>(new ResponseDTO<>(1, "예약 신청 완료", saveReservation.toResponse()), HttpStatus.OK);
    }
<<<<<<< HEAD
>>>>>>> cb21803 (Reservation save 완료)
=======

<<<<<<< HEAD
    // 푸쉬 알림 보내는 핸들러
    @PostMapping("/api/fcm")
    public ResponseEntity pushMessage(@RequestBody RequestDTO requestDTO) throws IOException {
        System.out.println(requestDTO.getTargetToken() + " "
                + requestDTO.getTitle() + " " + requestDTO.getBody());

        firebaseCloudMessageService.sendMessageTo(
                requestDTO.getTargetToken(),
                requestDTO.getTitle(),
                requestDTO.getBody());
        return ResponseEntity.ok().build();
    }

    // 앱 실행 후 토큰 보냄 -> server에서 받는 핸들러
    @PostMapping("/fcm/token")
    public ResponseEntity<?> pushMessage(@RequestBody String token) throws Exception {
        if (token != null) {
            System.out.println("앱 실행 후 토큰 전송 성공!, token : " + token);
            // token 받기 성공
            // token 받고, DB에 저장 후 푸쉬 알림 시 활용
        } else {
            System.out.println("토큰 전송 실패!");
        }
        return ResponseEntity.ok().build();
    }
>>>>>>> 1df70c2 (예약 완료 시 FCM 사용전 세팅완료)
=======
//    // 푸쉬 알림 보내는 핸들러
//    @PostMapping("/api/fcm")
//    public ResponseEntity pushMessage(@RequestBody RequestDTO requestDTO) throws IOException {
//        System.out.println(requestDTO.getTargetToken() + " "
//                + requestDTO.getTitle() + " " + requestDTO.getBody());
//
//        firebaseCloudMessageService.sendMessageTo(
//                requestDTO.getTargetToken(),
//                requestDTO.getTitle(),
//                requestDTO.getBody());
//        return ResponseEntity.ok().build();
//    }
//
//    // 앱 실행 후 토큰 보냄 -> server에서 받는 핸들러
//    @PostMapping("/fcm/token")
//    public ResponseEntity<?> pushMessage(@RequestBody String token) throws Exception {
//        if (token != null) {
//            System.out.println("앱 실행 후 토큰 전송 성공!, token : " + token);
//            // token 받기 성공
//            // token 받고, DB에 저장 후 푸쉬 알림 시 활용
//        } else {
//            System.out.println("토큰 전송 실패!");
//        }
//        return ResponseEntity.ok().build();
//    }
>>>>>>> ca77a8a (FMC 이용 하여 알림 기능 구현중)
}
