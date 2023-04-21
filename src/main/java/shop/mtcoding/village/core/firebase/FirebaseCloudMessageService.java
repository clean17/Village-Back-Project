package shop.mtcoding.village.core.firebase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FirebaseCloudMessageService {

<<<<<<< HEAD
<<<<<<< HEAD
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/fcmgood-41ac1/messages:send";
=======
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/village/messages:send";
>>>>>>> 1df70c2 (예약 완료 시 FCM 사용전 세팅완료)
=======
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/village-5b9d4/messages:send";
>>>>>>> ca77a8a (FMC 이용 하여 알림 기능 구현중)
    private final ObjectMapper objectMapper;

    public void sendMessageTo(String targetToken, String title, String body) throws IOException {
        String message = makeMessage(targetToken, title, body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request)
                .execute();

<<<<<<< HEAD
        System.out.println("디버그 : " + response);

=======
        System.out.println(response.body().string());
>>>>>>> 1df70c2 (예약 완료 시 FCM 사용전 세팅완료)
    }

    private String makeMessage(String targetToken, String title, String body) throws JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                        .token(targetToken)
                        .notification(FcmMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build()
                        )
                        .build()
                )
                .validate_only(false)
                .build();

        return objectMapper.writeValueAsString(fcmMessage);
    }

    private String getAccessToken() throws IOException {
<<<<<<< HEAD
<<<<<<< HEAD
        String firebaseConfigPath = "/firebase/fcmgood-41ac1-f214841e94d2.json";
=======
        String firebaseConfigPath = "/firebase/village-2c2c1-firebase-adminsdk-3iun2-94cdda073d.json";
>>>>>>> 1df70c2 (예약 완료 시 FCM 사용전 세팅완료)
=======
        String firebaseConfigPath = "/firebase/village-5b9d4-firebase-adminsdk-hqgb0-45e01a6b09.json";
>>>>>>> ca77a8a (FMC 이용 하여 알림 기능 구현중)

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

}