package com.fivedalant.allmyrental.Repository;

import com.fivedalant.allmyrental.Jpa.AfterService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AfterServiceRepository extends JpaRepository<AfterService, Long> {

    @Query(value = "SELECT * FROM after_service WHERE user_id = :user_id ORDER BY after_service_state ASC",nativeQuery = true)
    List<AfterService> seeAfterService(Long user_id)throws Exception;

    @Query(value = "SELECT * FROM after_service WHERE after_service_id = :after_service_id",nativeQuery = true)
    AfterService seeAfterServiceDetail(Long after_service_id)throws Exception;
}
