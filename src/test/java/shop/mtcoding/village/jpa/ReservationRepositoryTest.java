 package shop.mtcoding.village.jpa;

 import org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
 import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
 import org.springframework.test.context.junit.jupiter.SpringExtension;
 import org.springframework.transaction.annotation.Transactional;
 import shop.mtcoding.village.model.address.Address;
 import shop.mtcoding.village.model.place.Place;
 import shop.mtcoding.village.model.place.PlaceAddress;
 import shop.mtcoding.village.model.reservation.Reservation;
 import shop.mtcoding.village.model.reservation.ReservationRepository;
 import shop.mtcoding.village.model.review.Review;
 import shop.mtcoding.village.model.user.User;
 import shop.mtcoding.village.util.status.PlaceStatus;
 import shop.mtcoding.village.util.status.ReservationStatus;

 import javax.persistence.EntityManager;
 import java.time.LocalDateTime;
 import java.time.LocalTime;
 import java.util.List;
 import java.util.Optional;

 @DataJpaTest
 @ExtendWith(SpringExtension.class)
 @DisplayName("예약 JPA 테스트")
 public class ReservationRepositoryTest {

     @Autowired
     private ReservationRepository reservationRepository;

     @Autowired
     private TestEntityManager entityManager;

     @Test
     @Transactional
     @DisplayName("예약 조회 테스트")
     void selectAll() {
         List<Reservation> reservations = reservationRepository.findAll();
         Assertions.assertNotEquals(reservations.size(), 0);

         Reservation reservation = reservations.get(0);
         Assertions.assertEquals(reservation.getPeopleNum(), 2);
     }

     @Test
     @Transactional
     @DisplayName("예약 조회 및 수정 테스트")
     void selectAndUpdate() {
         var optionalReservation = this.reservationRepository.findById(1L);

         if(optionalReservation.isPresent()) {
             var result = optionalReservation.get();
             Assertions.assertEquals(result.getPeopleNum(), 2);

             var peopleNum = 4;
             result.setPeopleNum(peopleNum);
             Reservation merge = entityManager.merge(result);

             Assertions.assertEquals(merge.getPeopleNum(), 4);
         } else {
             Assertions.assertNotNull(optionalReservation.get());
         }
     }

     @Test
     @Transactional
     @DisplayName("예약 삽입 및 삭제 테스트")
     void insertAndDelete() {
         Reservation reservation = setUp(LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 5, ReservationStatus.WAIT);

         Optional<Reservation> findReservation = this.reservationRepository.findById(reservation.getId());

         if(findReservation.isPresent()) {
             var result = findReservation.get();
             Assertions.assertEquals(result.getPeopleNum(), 5);
             entityManager.remove(reservation);
             Optional<Reservation> deleteReservation = this.reservationRepository.findById(reservation.getId());
             if (deleteReservation.isPresent()) {
                 Assertions.assertNull(deleteReservation.get());
             }
         } else {
             Assertions.assertNotNull(findReservation.get());
         }
     }
     private Reservation setUp(LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, Integer peopleNum, ReservationStatus status) {
         User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
         this.entityManager.persist(user);

         PlaceAddress placeAddress = new PlaceAddress().builder().address("도로명주소").sigungu("시군구").zonecode("우편번호").x("경도").y("위도").build();
         this.entityManager.persist(placeAddress);

         Place place = new Place().builder().title("제목").tel("123123").placeIntroductionInfo("공간정보").notice("공간소개").isConfirmed(true).status(PlaceStatus.WAIT)
                 .startTime(LocalDateTime.now()).endTime(LocalDateTime.now()).build();
         this.entityManager.persist(place);

         var reservation = new Reservation();
         reservation.setUser(user);
         reservation.setPlace(place);
         reservation.setDate(date);
         reservation.setStartTime(startTime);
         reservation.setEndTime(endTime);
         reservation.setPeopleNum(peopleNum);
         reservation.setStatus(status);
         return  this.entityManager.persist(reservation);
     }
 }
