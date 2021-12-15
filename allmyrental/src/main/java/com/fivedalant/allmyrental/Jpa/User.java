package com.fivedalant.allmyrental.Jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//유저
@Entity
@Table(name="user",uniqueConstraints = {@UniqueConstraint(columnNames = {"user_login_id"})})
public class User {

    @Id
    @GeneratedValue
    private Long user_id;           //유저 고유 아이디
    private String user_login_id;   //유저 로그인 아이디
    private String password;        //유저 비밀번호
    private String user_name;       //유저 이름
    private String phone_number;    //유저 전화번호
    private String favorite;        //유저 관심사
    private String consider;        //유저 고려사항
    private int grade;              //유저 등급
    private int user_push;          //유저 푸시 여부
    private int user_terms;         //유저 약관 동의 여부
    private int auto_login = 0;     //유저 자동 로그인 여부
    private String push_token;      //유저 푸쉬 토큰
    @Temporal(TemporalType.DATE)
    private Date birth;             //유저 생년월일

    @Temporal(TemporalType.TIMESTAMP)
    private Date user_regdate;      //유저 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date user_update;       //유저 수정일

    @Temporal(TemporalType.TIMESTAMP)
    private Date login_date;        //유저 로그인 시간

    public String getConsider() {
        return consider;
    }

    public void setConsider(String consider) {
        this.consider = consider;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getUser_push() {
        return user_push;
    }

    public void setUser_push(int user_push) {
        this.user_push = user_push;
    }

    public int getUser_terms() {
        return user_terms;
    }

    public void setUser_terms(int user_terms) {
        this.user_terms = user_terms;
    }

    public int getAuto_login() {
        return auto_login;
    }

    public void setAuto_login(int auto_login) {
        this.auto_login = auto_login;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getUser_regdate() {
        return user_regdate;
    }

    public void setUser_regdate(Date user_regdate) {
        this.user_regdate = user_regdate;
    }

    public Date getUser_update() {
        return user_update;
    }

    public void setUser_update(Date user_update) {
        this.user_update = user_update;
    }

    public Date getLogin_date() {
        return login_date;
    }

    public void setLogin_date(Date login_date) {
        this.login_date = login_date;
    }

    public String getUser_login_id() {
        return user_login_id;
    }

    public void setUser_login_id(String user_login_id) {
        this.user_login_id = user_login_id;
    }

    public String getPush_token() {
        return push_token;
    }

    public void setPush_token(String push_token) {
        this.push_token = push_token;
    }
}
