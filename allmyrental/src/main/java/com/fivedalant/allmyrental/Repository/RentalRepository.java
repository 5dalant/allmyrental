package com.fivedalant.allmyrental.Repository;

import com.fivedalant.allmyrental.DTO.ScheduleDto;
import com.fivedalant.allmyrental.Jpa.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    //렌탈 개수
    @Query(value = "SELECT COUNT(*) FROM rental WHERE user_id = :user_id", nativeQuery = true)
    int userRentalCount(Long user_id) throws Exception;

    //렌탈 리스트 가져오기
    @Query(value = "SELECT * FROM rental WHERE user_id = :user_id ORDER BY contact_last_date ASC LIMIT :offset ,5", nativeQuery = true)
    List<Rental> seeMyRentals(Long user_id, int offset) throws Exception;

    @Query(value = "SELECT COUNT(*) FROM rental WHERE user_id = :user_id", nativeQuery = true)
    int countMyRentals(Long user_id) throws Exception;

    //렌탈 상세보기 가져오기
    @Query(value = "SELECT * FROM rental WHERE rental_id = :rental_id", nativeQuery = true)
    Rental seeMyRentalDetail(Long rental_id) throws Exception;

    @Query(value = "SELECT user_id, COUNT(user_id) AS count_contract FROM rental WHERE TIMESTAMPDIFF(DAY, CURDATE(),contact_last_date) < 7 AND TIMESTAMPDIFF(DAY, CURDATE(),contact_last_date) >= 0 GROUP BY user_id", nativeQuery = true)
    List<ScheduleDto> seeSchedule() throws Exception;
}
