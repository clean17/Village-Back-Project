package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.imagemap.request.ImageMapSaveRequest;
import shop.mtcoding.village.dto.imagemap.response.MapDTO;
import shop.mtcoding.village.model.map.ImageMap;
import shop.mtcoding.village.model.map.ImageMapRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceJpaRepository;
import shop.mtcoding.village.model.place.PlaceRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MapService {
    private final ImageMapRepository imageMapRepository;

    private final PlaceJpaRepository placeRepository;


    public List<MapDTO> getPlaceMap() {

        try {
            List<Place> places = placeRepository.findAll();
            List<MapDTO> mapDTOs = new ArrayList<>();
            for (Place place : places) {
                MapDTO mapDTO = new MapDTO();
                mapDTO.setId(place.getId());
                mapDTO.setTitle(place.getTitle());
                mapDTO.setX(place.getAddress().getX());
                mapDTO.setY(place.getAddress().getY());
                mapDTOs.add(mapDTO);
            }
            return mapDTOs;
        }catch (Exception500 e) {
            throw new Exception500("MAP 좌표 저장 실패 " + e.getMessage());
        }

    }







    @Transactional
    public ImageMap saveMapUrl(ImageMapSaveRequest imageMapSaveRequest) {
        try {
            // Google Maps Static API endpoint
            String url = "https://maps.googleapis.com/maps/api/staticmap";

            // 요청 파라미터
            String parameters = String.format(
                    "center=%f,%f&zoom=%d&size=400x400&markers=color:red%%7Clabel:%%7C%f,%f&key=%s",
                    imageMapSaveRequest.getLat(), imageMapSaveRequest.getLng(), imageMapSaveRequest.getZoom(),
                    imageMapSaveRequest.getLat(), imageMapSaveRequest.getLng(), "AIzaSyDmvb-5cgAOEvdoYoPt0jDUmxLpsW5aNvg" //api키
            );

            // 요청 URL 생성
            String requestUrl = url + "?" + parameters;

            ImageMap imageMap = imageMapSaveRequest.toEntity(requestUrl);

            return imageMapRepository.save(imageMap);
        } catch (Exception500 e) {
            throw new Exception500("구글맵 url 저장 실패 " + e.getMessage());
        }
    }


}
