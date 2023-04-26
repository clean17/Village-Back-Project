//package shop.mtcoding.village.model.completePayment;
//
//import lombok.Data;
//import shop.mtcoding.village.cardData.CardData;
//
//import javax.persistence.Id;
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.Map;
//
//@Data
//@Table(name = "complete_payment_tb")
//public class CompletePayment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "receipt_id")
//    private String receiptId;
//
//    @Column(name = "order_id")
//    private String orderId;
//
//    @Column(name = "price")
//    private int price;
//
//    @Column(name = "tax_free")
//    private int taxFree;
//
//    @Column(name = "cancelled_price")
//    private int cancelledPrice;
//
//    @Column(name = "cancelled_tax_free")
//    private int cancelledTaxFree;
//
//    @Column(name = "order_name")
//    private String orderName;
//
//    @Column(name = "company_name")
//    private String companyName;
//
//    @Column(name = "gateway_url")
//    private String gatewayUrl;
//
//    @Column(name = "metadata")
////    @Convert(converter = JsonConverter.class)
//    private Map<String, Object> metadata;
//
//    @Column(name = "sandbox")
//    private boolean sandbox;
//
//    @Column(name = "pg")
//    private String pg;
//
//    @Column(name = "method")
//    private String method;
//
//    @Column(name = "method_symbol")
//    private String methodSymbol;
//
//    @Column(name = "method_origin")
//    private String methodOrigin;
//
//    @Column(name = "method_origin_symbol")
//    private String methodOriginSymbol;
//
//    @Column(name = "purchased_at")
//    private LocalDateTime purchasedAt;
//
//    @Column(name = "requested_at")
//    private LocalDateTime requestedAt;
//
//    @Column(name = "status_locale")
//    private String statusLocale;
//
//    @Column(name = "currency")
//    private String currency;
//
//    @Column(name = "receipt_url")
//    private String receiptUrl;
//
//    @Column(name = "status")
//    private int status;
//
//
//    private CardData cardData;
//
//    // getter, setter 생략
//}
