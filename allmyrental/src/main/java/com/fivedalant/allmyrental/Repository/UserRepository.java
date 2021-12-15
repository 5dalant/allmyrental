package com.fivedalant.allmyrental.Repository;

import com.fivedalant.allmyrental.Jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface UserRepository extends JpaRepository<User , Long> {

    //로그인
    @Query(value = "SELECT * FROM user WHERE user_login_id = :user_login_id",nativeQuery = true)
    User login(String user_login_id) throws Exception;

    //아이디 중복 확인
    @Query(value = "SELECT COUNT(*) FROM user WHERE user_login_id = :user_login_id",nativeQuery = true)
    int idCheck(String user_login_id) throws Exception;

    //유저 상세보기
    @Query(value = "SELECT * FROM user WHERE user_id = :user_id",nativeQuery = true)
    User seeUser(Long user_id)throws Exception;

    @Query(value = "SELECT user_login_id FROM user WHERE user_name = :user_name AND phone_number = :phone_number AND birth = :birth ",nativeQuery = true)
    String inquiryId(String user_name, String phone_number, Date birth) throws Exception;

    @Query(value = "SELECT user_id FROM user WHERE user_login_id = :user_login_id AND user_name = :user_name AND phone_number = :phone_number AND birth = :birth ",nativeQuery = true)
    Long inquiryPw(String user_login_id,String user_name, String phone_number, Date birth) throws Exception;
}
