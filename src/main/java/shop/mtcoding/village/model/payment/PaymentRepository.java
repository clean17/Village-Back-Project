package shop.mtcoding.village.model.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.search.Search;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Optional<Payment> findByOrderIdAndOrderNameAndTotalPrice(String orderId, String orderName, Integer totalPrice);
}
