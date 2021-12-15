package com.fivedalant.allmyrental.Repository;

import com.fivedalant.allmyrental.Jpa.RegularManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegularManageRepository extends JpaRepository<RegularManage,Long> {

    @Query(value = "SELECT * FROM regular_manage WHERE regular_manage_id = :regular_manage_id",nativeQuery = true)
    RegularManage seeRegularManage(Long regular_manage_id) throws Exception;
}
