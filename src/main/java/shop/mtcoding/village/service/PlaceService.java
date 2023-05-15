package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.api.s3.S3Service;
import shop.mtcoding.village.core.exception.CustomException;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.date.request.DateSaveDTO;
import shop.mtcoding.village.dto.facilityInfo.request.FacilityInfoSaveDTO;
import shop.mtcoding.village.dto.file.dto.FileSaveDTO;
import shop.mtcoding.village.dto.hashtag.request.HashtagSaveDTO;
import shop.mtcoding.village.dto.host.HostDTO;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.dto.place.request.PlaceUpdateRequest;
import shop.mtcoding.village.dto.place.response.DetailPlaceResponse;
import shop.mtcoding.village.dto.place.response.PlaceList;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.category.CategoryRepository;
import shop.mtcoding.village.model.date.DateRepository;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.facilityInfo.FacilityInfoRepository;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.model.file.FileInfoRepository;
import shop.mtcoding.village.model.file.FileRepository;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.hashtag.HashtagRepository;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.host.HostRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceJpaRepository;
import shop.mtcoding.village.model.place.PlaceRepository;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.review.ReviewRepository;
import shop.mtcoding.village.model.scrap.Scrap;
import shop.mtcoding.village.model.scrap.ScrapRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.Base64Decoded;
import shop.mtcoding.village.util.status.PlaceStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    private final PlaceJpaRepository placeJpaRepository;

    private final DateRepository dateRepository;

    private final HashtagRepository hashtagRepository;

    private final FacilityInfoRepository facilityInfoRepository;

    private final ReviewRepository reviewRepository;

    private final CategoryRepository categoryRepository;

    private final FileService fileService;

    private final FileRepository fileRepository;

    private final FileInfoRepository fileInfoRepository;

    private final HostRepository hostRepository;

    private final ScrapRepository scrapRepository;

    private final S3Service s3Service;

    @Transactional
    public List<PlaceList> 공간리스트() {

        try {
            return placeRepository.PlaceList();
        }catch (Exception500 e) {
            throw new Exception500("공간리스트 오류" + e.getMessage());
        }
    }

    // TODO file 이름 고유값
    @Transactional
    public Place 공간등록하기(PlaceSaveRequest placeRequest, User user) {
        try {

            // 공간 insert
            placeRequest.setStatus(PlaceStatus.INACTIVE);
            placeRequest.setUser(user);

            Place savePlace = placeJpaRepository.save(placeRequest.toEntity());

            Optional<Place> byId = placeJpaRepository.findById(savePlace.getId());

            Place place = byId.get();

            // 해시태그 insert
            List<Hashtag> hashtagList = new ArrayList<Hashtag>();

            for (HashtagSaveDTO.HashtagSaveDto hash : placeRequest.getHashtag()) {
                Hashtag save1 = hashtagRepository.save(hash.toEntity(hash.getHashtagName(), place));

                hashtagList.add(save1);
            }

            // file s3에 저장
            List<File> fileList = new ArrayList<>();

            for (FileSaveDTO.FileSaveDto files : placeRequest.getFile()) {
                String imgPath = s3Service.upload(files.getFileName(), Base64Decoded.convertBase64ToMultipartFile(files.getFileUrl()));
                files.setFileUrl(imgPath + ".jpg");

                System.out.println("디버그 : " + imgPath);

                File save = fileRepository.save(files.toEntity(files.getFileName(), files.getFileUrl(), place));
                fileList.add(save);

//                fileService.save(placeRequest.getFile().get(0));
            }

            // 카테고리 insert
            Category category = new Category();
            category.setCategoryName(placeRequest.getCategoryName());
            category.setPlace(place);
            categoryRepository.save(category);

            // 요일 날짜 insert
            List<Dates> dateList = new ArrayList<Dates>();

            for (DateSaveDTO.DateSaveDto date : placeRequest.getDayOfWeek()) {
                Dates saveDate = dateRepository.save(date.toEntity(date.getDayOfWeekName(), place));

                dateList.add(saveDate);
            }

            // 편의 시설 insert
            List<FacilityInfo> facilityInfoList = new ArrayList<FacilityInfo>();

            for (FacilityInfoSaveDTO.FacilityInfoSaveDto facilityInfo : placeRequest.getFacilityInfo()) {
                FacilityInfo savefacilityInfo = facilityInfoRepository.save(facilityInfo.toEntity(facilityInfo.getFacilityName(), place));

                facilityInfoList.add(savefacilityInfo);
            }


            return savePlace;
        } catch (Exception500 e) {
            throw new Exception500("공간등록 오류" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public Optional<Place> getPlace(Long id) {
        return placeJpaRepository.findById(id);
    }

    @Transactional
    public Place 공간비활성화(Place place) {
        try {

            place.setStatus(PlaceStatus.INACTIVE);
            return placeJpaRepository.save(place);
        } catch (Exception500 e) {
            throw new Exception500("공간비활성화 오류" + e.getMessage());
        }

    }

    @Transactional
    public Place 공간활성화(Place place) {
        try {

            place.setStatus(PlaceStatus.ACTIVE);
            return placeJpaRepository.save(place);
        } catch (Exception500 e) {
            throw new Exception500("공간활성화 오류" + e.getMessage());
        }

    }

    @Transactional
    public Place 공간수정하기(PlaceUpdateRequest placeUpdateRequest, User user) {
        try {

            // 공간 insert
            placeUpdateRequest.setStatus(PlaceStatus.INACTIVE);
            placeUpdateRequest.setUser(user);

            Place savePlace = placeJpaRepository.save(placeUpdateRequest.toEntity());

            Optional<Place> byId = placeJpaRepository.findById(savePlace.getId());

            Place place = byId.get();

            // 해시태그 insert
            List<Hashtag> hashtagList = new ArrayList<Hashtag>();

            for (HashtagSaveDTO.HashtagSaveDto hash : placeUpdateRequest.getHashtag()) {
                Hashtag save1 = hashtagRepository.save(hash.toEntity(hash.getHashtagName(), place));

                hashtagList.add(save1);
            }

            // file s3에 저장
            List<File> fileList = new ArrayList<>();

            for (FileSaveDTO.FileSaveDto files : placeUpdateRequest.getFile()) {
                String imgPath = s3Service.upload(files.getFileName(), Base64Decoded.convertBase64ToMultipartFile(files.getFileUrl()));
                files.setFileUrl(imgPath + ".jpg");
                System.out.println("디버그 : " + imgPath);

                File save = fileRepository.save(files.toEntity(files.getFileName(), files.getFileUrl(), place));
                fileList.add(save);

//                fileService.save(placeRequest.getFile().get(0));
            }

            // 카테고리 insert
            Category category = new Category();
            category.setCategoryName(placeUpdateRequest.getCategoryName());
            category.setPlace(place);
            categoryRepository.save(category);

            // 요일 날짜 insert
            List<Dates> dateList = new ArrayList<Dates>();

            for (DateSaveDTO.DateSaveDto date : placeUpdateRequest.getDayOfWeek()) {
                Dates saveDate = dateRepository.save(date.toEntity(date.getDayOfWeekName(), place));

                dateList.add(saveDate);
            }

            // 편의 시설 insert
            List<FacilityInfo> facilityInfoList = new ArrayList<FacilityInfo>();

            for (FacilityInfoSaveDTO.FacilityInfoSaveDto facilityInfo : placeUpdateRequest.getFacilityInfo()) {
                FacilityInfo savefacilityInfo = facilityInfoRepository.save(facilityInfo.toEntity(facilityInfo.getFacilityName(), place));

                facilityInfoList.add(savefacilityInfo);
            }


            return savePlace;
        } catch (Exception500 e) {
            throw new Exception500("공간등록 오류" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Place> 공간메인보기() {
        return placeJpaRepository.findAll();
    }

    public Place 공간상세보기(Long placePathId, DetailPlaceResponse detailPlaceResponse) {

        // place 정보 넣기
        Optional<Place> placeOptional = placeJpaRepository.findById(placePathId);
        if (placeOptional.isEmpty()){
            throw new CustomException("공간의 정보를 찾을 수 없습니다");
        }
        Place place = placeOptional.get();
        var placeId = placeOptional.get().getId();

        // file 정보 넣기
        List<File> file = fileRepository.findByPlaceId(placeId);
        detailPlaceResponse.setFile(file);

//        FileInfo fileInfo = fileInfoRepository.findByType(FileType.PLACE);
//        detailPlaceResponse.setFile(fileInfo);

        // host 정보 넣기
        Host host = hostRepository.findByPlaceId(placeId);
        HostDTO hostDTO = new HostDTO();
        hostDTO.setId(hostDTO.getId());
        hostDTO.setHostName(hostDTO.getHostName());
        detailPlaceResponse.setHost(hostDTO);

        // review 정보 넣기
        List<Review> review = reviewRepository.findByPlaceId(placeId);
        detailPlaceResponse.setReview(review);

        // facility 정보 넣기
        List<FacilityInfo> facilityList = facilityInfoRepository.findByPlaceId(placeId);
        detailPlaceResponse.setFacilitys(facilityList);

        // hashtag 정보 넣기
        List<Hashtag> hashtag = hashtagRepository.findByPlaceId(placeId);
        detailPlaceResponse.setHashtags(hashtag);

        // date 정보 넣기
        List<Dates> dates = dateRepository.findByPlaceId(placeId);
        detailPlaceResponse.setDayOfWeeks(dates);

        // scrap 정보 넣기
        Scrap scrap = scrapRepository.findByPlaceId(placeId);
        detailPlaceResponse.setScrap(null);

        // category 정보 넣기
//        Optional<Category> categoryOptional = categoryRepository.findByPlaceId(placeId);
//        if (categoryOptional.isEmpty()){
//            throw new CustomException("카테고리에 대한 정보가 없습니다.");
//        }

//        Category category = categoryOptional.get();

        Category category = categoryRepository.findByPlaceId(placeId);
        detailPlaceResponse.setCategoryName(category.getCategoryName());

        System.out.println("디버그 : " + detailPlaceResponse);

        return place;
    }

    public Place 등록된공간보기(Long placePathId, DetailPlaceResponse detailPlaceResponse) {
        // place 정보 넣기
        Optional<Place> placeOptional = placeJpaRepository.findById(placePathId);
        if (placeOptional.isEmpty()){
            throw new CustomException("공간의 정보를 찾을 수 없습니다");
        }
        Place place = placeOptional.get();
        var placeId = placeOptional.get().getId();

        // file 정보 넣기
        List<File> file = fileRepository.findByPlaceId(placeId);
        detailPlaceResponse.setFile(file);

//        FileInfo fileInfo = fileInfoRepository.findByType(FileType.PLACE);
//        detailPlaceResponse.setFile(fileInfo);

        // host 정보 넣기
        Host host = hostRepository.findByPlaceId(placeId);
        HostDTO hostDTO = new HostDTO();
        hostDTO.setId(hostDTO.getId());
        hostDTO.setHostName(hostDTO.getHostName());
        detailPlaceResponse.setHost(hostDTO);

        // review 정보 넣기
        List<Review> review = reviewRepository.findByPlaceId(placeId);
        detailPlaceResponse.setReview(review);

        // facility 정보 넣기
        List<FacilityInfo> facilityList = facilityInfoRepository.findByPlaceId(placeId);
        detailPlaceResponse.setFacilitys(facilityList);

        // hashtag 정보 넣기
        List<Hashtag> hashtag = hashtagRepository.findByPlaceId(placeId);
        detailPlaceResponse.setHashtags(hashtag);

        // date 정보 넣기
        List<Dates> dates = dateRepository.findByPlaceId(placeId);
        detailPlaceResponse.setDayOfWeeks(dates);

        // scrap 정보 넣기
        Scrap scrap = scrapRepository.findByPlaceId(placeId);
        detailPlaceResponse.setScrap(null);

        // category 정보 넣기
        Category category = categoryRepository.findByPlaceId(placeId);
        detailPlaceResponse.setCategoryName(category.getCategoryName());

        System.out.println("디버그 : " + detailPlaceResponse);

        return place;

    }
}