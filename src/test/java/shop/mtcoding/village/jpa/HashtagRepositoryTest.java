 package shop.mtcoding.village.jpa;


 import org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
 import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
 import org.springframework.test.context.junit.jupiter.SpringExtension;
 import org.springframework.transaction.annotation.Transactional;
 import shop.mtcoding.village.model.address.Address;
 import shop.mtcoding.village.model.category.Category;
 import shop.mtcoding.village.model.date.Dates;
 import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
 import shop.mtcoding.village.model.hashtag.Hashtag;
 import shop.mtcoding.village.model.hashtag.HashtagRepository;
 import shop.mtcoding.village.model.place.Place;
 import shop.mtcoding.village.model.place.PlaceAddress;
 import shop.mtcoding.village.model.review.Review;
 import shop.mtcoding.village.model.user.User;

 import javax.persistence.EntityManager;
 import java.time.LocalDateTime;
 import java.time.LocalTime;
 import java.util.Collections;
 import java.util.List;
 import java.util.Optional;

 @DataJpaTest
 @ExtendWith(SpringExtension.class)
 @DisplayName("해시태그 JPA 테스트")
 public class HashtagRepositoryTest {

     @Autowired
     private HashtagRepository hashtagInfoRepository;

     @Autowired
     private TestEntityManager entityManager;

     @Autowired
     private EntityManager em;

 //    @BeforeEach
 //    public void init() {
 //        em.createNativeQuery("ALTER TABLE hashtag_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
 //        setUp("독서실");
 //    }

     @Test
     @Transactional
     @DisplayName("해시태그 조회 테스트")
     void selectAll() {
         List<Hashtag> hashtags = hashtagInfoRepository.findAll();
         Assertions.assertNotEquals(hashtags.size(), 0);

         Hashtag hashtag = hashtags.get(0);
         Assertions.assertEquals(hashtag.getHashtagName(), "파티룸");
     }

     @Test
     @Transactional
     @DisplayName("해시태그 조회 및 수정 테스트")
     void selectAndUpdate() {
         var optionalHashtag = this.hashtagInfoRepository.findById(1L);

         if (optionalHashtag.isPresent()) {
             var result = optionalHashtag.get();
             Assertions.assertEquals(result.getHashtagName(), "파티룸");

             String hashtag = "음식점";
             result.setHashtagName(hashtag);
             Hashtag merge = entityManager.merge(result);

             Assertions.assertEquals(merge.getHashtagName(), "음식점");
         } else {
             Assertions.assertNotNull(optionalHashtag.get());
         }
     }

     private Hashtag setUp(String hashtagName1) {

         User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
         this.entityManager.persist(user);

         PlaceAddress placeAddress = new PlaceAddress().builder().address("도로명주소").sigungu("시군구").zonecode("우편번호").x("경도").y("위도").build();
         this.entityManager.persist(placeAddress);

         Place place = new Place().builder().title("제목").tel("123123").placeIntroductionInfo("공간정보").notice("공간소개")
                 .startTime(LocalDateTime.now()).endTime(LocalDateTime.now()).build();
         this.entityManager.persist(place);

         Hashtag hashtagName = new Hashtag().builder().hashtagName(hashtagName1).build();
         return this.entityManager.persist(hashtagName);
     }
 }

