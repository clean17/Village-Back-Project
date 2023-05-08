package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.search.SearchList;
import shop.mtcoding.village.dto.search.SearchOrderby;
import shop.mtcoding.village.dto.search.SearchRequest;
import shop.mtcoding.village.model.search.Search;
import shop.mtcoding.village.model.search.SearchJpaRepository;
import shop.mtcoding.village.model.search.SearchRepository;
import shop.mtcoding.village.model.user.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;
    private final SearchJpaRepository searchJpaRepository;

    @Transactional
    public List<SearchList> 검색(String keyword) {
        try {
            List<SearchList> searchLists = searchRepository.searchPlacesByKeyword(keyword);
            return searchLists;
        } catch (Exception500 e) {
            throw new Exception500("검색 오류" + e.getMessage());
        }

    }


    @Transactional
    public void 키워드저장(SearchRequest.SaveSearch saveSearch) {
        try {
            Search searchPS = saveSearch.toEntity();
            searchJpaRepository.save(searchPS);

        } catch (Exception500 e) {
            throw new Exception500("키워드저장 오류" + e.getMessage());
        }

    }


    @Transactional
    public List<SearchOrderby> 높은가격순정렬() {

        try {
            return searchRepository.searchPlacesByPriceDescending();
        }catch (Exception500 e) {
            throw new Exception500("높은가격순정렬 오류" + e.getMessage());
        }
    }
    @Transactional
    public List<SearchOrderby> 낮은가격순정렬() {

        try {
            return searchRepository.searchPlacesByPriceAscending();
        }catch (Exception500 e) {
            throw new Exception500("낮은가격순정렬 오류" + e.getMessage());
        }
    }
    @Transactional
    public List<SearchOrderby> 별점높은순정렬() {

        try {
            return searchRepository.searchPlacesByRatingDescending();
        }catch (Exception500 e) {
            throw new Exception500("낮은가격순정렬 오류" + e.getMessage());
        }
    }

}
