//package shop.mtcoding.village.model.receipt;
//
//import lombok.Getter;
//import lombok.Setter;
//import shop.mtcoding.village.cardData.CardData;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.Map;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "receipt_tb")
//public class Receipt {
//
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
////    @Column(name = "metadata")
////    private Map<String, Object> metadata;
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
//    @Column(name = "cancelled_at")
//    private LocalDateTime cancelledAt;
//
//    @Column(name = "requested_at")
//    private LocalDateTime requestedAt;
//
//    @Column(name = "status_locale")
//    private String statusLocale;
//
//    @Column(name = "receipt_url")
//    private String receiptUrl;
//
//    @Column(name = "status")
//    private int status;
//
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "tid", column = @Column(name = "tid")),
//            @AttributeOverride(name = "cardApproveNo", column = @Column(name = "card_approve_no")),
//            @AttributeOverride(name = "cardNo", column = @Column(name = "card_no")),
//            @AttributeOverride(name = "cardQuota", column = @Column(name = "card_quota")),
//            @AttributeOverride(name = "cardCompanyCode", column = @Column(name = "card_company_code")),
//            @AttributeOverride(name = "cardCompany", column = @Column(name = "card_company"))
//    })
//    private CardData cardData;
//
//
//
//
//    // constructors, getters, and setters
//}
