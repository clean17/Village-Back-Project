package shop.mtcoding.village.api.firebase;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Component
@RequiredArgsConstructor
public class FirebaseCloudMessageService {

    private final String API_URL = "https://fcm.googleapis.com/v1/projects/fcmgood-41ac1/messages:send";

    private final ObjectMapper objectMapper;

    public void sendMessageTo(String targetToken, String title, String body) throws IOException {
        String message = makeMessage(targetToken, title, body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
        System.out.println("firebase1111111111111");
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();
        System.out.println("444444444444444444444444444444444444");
        Response response = client.newCall(request)
                .execute();
        System.out.println("5555555555555555555555555555555");

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
        String bucketName = "villages-3";
        String objectKey = "fcmgood-41ac1-f214841e94d2.json";
        S3Object s3Object = null;

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();
            s3Object = s3Client.getObject(bucketName, objectKey);
        } catch (AmazonServiceException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

        try {
            InputStream inputStream = s3Object.getObjectContent();
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(inputStream)
                    .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
            googleCredentials.refreshIfExpired();
            return googleCredentials.getAccessToken().getTokenValue();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
            return "";
        }

//        System.out.println("1111111111111111111111111111111111");
//        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//                .withRegion(Regions.AP_NORTHEAST_2) // 리전 설정
//                .build();
//        S3Object o = s3.getObject("villages-3", "fcmgood-41ac1-f214841e94d2.json");
//        System.out.println("2222222222222222222222222222222222");
//        S3ObjectInputStream s3is = o.getObjectContent();
//        System.out.println("333333333333333333333333333");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(s3is));
//        String line;
//        StringBuffer sb = new StringBuffer();
//
//        while((line = reader.readLine()) != null) {
//            System.out.println("디버그 line : "+line);
//            sb.append(line);
//        }
//
//        reader.close();
//        s3is.close();
////        String firebaseConfigPath = sb.toString();
//        String firebaseConfigPath = sb.toString();
//
//
//        System.out.println("디버그 최종 : " +firebaseConfigPath);
//
//        try {
//            FileWriter fileWriter = new FileWriter("src/main/resources/firebase/path.json");
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            bufferedWriter.write(firebaseConfigPath);
//            bufferedWriter.close();
//            System.out.println("디버그 try");
//        } catch (IOException e) {
//            System.out.println("디버그 catch");
//            e.printStackTrace();
//        }
//
//        String firebasePath = "/firebase/path.json";
//
//
//        GoogleCredentials googleCredentials = GoogleCredentials
//                .fromStream(new ClassPathResource(firebasePath).getInputStream())
//                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
//
//        googleCredentials.refreshIfExpired();
//        return googleCredentials.getAccessToken().getTokenValue();
    }

}