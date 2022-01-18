package com.fivedalant.allmyrental.Repository;

import com.fivedalant.allmyrental.Jpa.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchRepository extends JpaRepository<Search,Long> {

    //추천 검색어
    @Query(value = "SELECT * FROM search WHERE search_name LIKE CONCAT('%',:searchWord,'%') ORDER BY search_hits desc Limit 0,5",nativeQuery = true)
    List<Search> seeRecommendSearch(String searchWord);

    //검색어 존재 여부
    @Query(value = "SELECT * FROM search WHERE search_name = :searchWord",nativeQuery = true)
    Search seeExistence(String searchWord);
}
