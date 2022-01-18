package com.fivedalant.allmyrental.Repository;

import com.fivedalant.allmyrental.Jpa.RecentSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecentSearchRepository extends JpaRepository<RecentSearch, Long> {


    @Query(value = "SELECT * FROM recent_search WHERE user_id = :user_id order by recent_search_regdate desc limit 0, 10",nativeQuery = true)
    List<RecentSearch> seeRecentSearch(Long user_id);
}
