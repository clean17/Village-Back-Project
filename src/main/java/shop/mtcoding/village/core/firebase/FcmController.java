package shop.mtcoding.village.core.firebase;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import com.google.firebase.messaging.FirebaseMessaging;
>>>>>>> ca77a8a (FMC 이용 하여 알림 기능 구현중)
=======
>>>>>>> 8bed703 (FMC 이용 하여 알림 테스트 완료)
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FcmController {

    private final FirebaseCloudMessageService firebaseCloudMessageService;

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


<<<<<<< HEAD
<<<<<<< HEAD


=======
>>>>>>> ca77a8a (FMC 이용 하여 알림 기능 구현중)
=======


>>>>>>> 8bed703 (FMC 이용 하여 알림 테스트 완료)
}
