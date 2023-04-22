package shop.mtcoding.village.dto.facilityInfo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.place.Place;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FacilityInfoSaveDTO {
<<<<<<< HEAD
    private List<FacilityInfoDTO> facilityName;
=======
    private FacilityInfo facilityName;
>>>>>>> 0a7ac65 (fcm 완료)

    @Setter
    @Getter
    @ToString
    public static class FacilityInfoDTO extends FacilityInfo {
        private String facilityName;
        private Place placeId;

<<<<<<< HEAD
        public FacilityInfo toEntity(String name, Place id) {
            FacilityInfo facilityInfoName = new FacilityInfo();
            facilityInfoName.setPlace(id);
            facilityInfoName.setFacilityName(name);
            return facilityInfoName;
        }
=======
        FacilityInfo facilityInfo = new FacilityInfo();
        facilityInfo.setFacilityName(facilityName.toString());
        return facilityInfo;
>>>>>>> 0a7ac65 (fcm 완료)
    }
}
