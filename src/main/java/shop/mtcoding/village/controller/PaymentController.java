package shop.mtcoding.village.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.dto.ReceiptDTO;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.model.payment.PaymentRepository;
import shop.mtcoding.village.service.PaymentService;

import java.util.HashMap;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    private final PaymentService paymentService;

    private final ObjectMapper objectMapper;

    public PaymentController(PaymentRepository paymentRepository, PaymentService paymentService, ObjectMapper objectMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReceiptDTO receiptDTO) throws JsonProcessingException {

        System.out.println("디버그 : " + objectMapper.writeValueAsString(receiptDTO));

        HashMap<Object, Object> map = new HashMap<>();

        map.put("success", true);

        return new ResponseEntity(new ResponseDTO<>(1, 200, "결제요청", map), HttpStatus.OK);
    }
}
