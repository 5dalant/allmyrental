package com.fivedalant.allmyrental.Jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

// 정기관리
@Entity
@Table(name="regular_manage")
public class RegularManage {

    @Id
    @GeneratedValue
    private Long regular_manage_id;                 //정기관리 아이디
    private int regular_manage_date;                //정기관리 날짜
    private int regular_manage_notification;         //정기관리 알림 여부
    private int regular_manage_notification_date;    //정기관리 알림 날짜

    @Temporal(TemporalType.TIMESTAMP)
    private Date regular_manage_regdate;            //정기관리 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date regular_manage_update;             //정기관리 수정일

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")                 //렌탈 아이디
    private Rental rental;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")                     //유저 아이디
    private User user;

    public Long getRegular_manage_id() {
        return regular_manage_id;
    }

    public void setRegular_manage_id(Long regular_manage_id) {
        this.regular_manage_id = regular_manage_id;
    }

    public int getRegular_manage_date() {
        return regular_manage_date;
    }

    public void setRegular_manage_date(int regular_manage_date) {
        this.regular_manage_date = regular_manage_date;
    }

    public int getRegular_manage_notification() {
        return regular_manage_notification;
    }

    public void setRegular_manage_notification(int regular_manage_notification) {
        this.regular_manage_notification = regular_manage_notification;
    }

    public int getRegular_manage_notification_date() {
        return regular_manage_notification_date;
    }

    public void setRegular_manage_notification_date(int regular_manage_notification_date) {
        this.regular_manage_notification_date = regular_manage_notification_date;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public Date getRegular_manage_regdate() {
        return regular_manage_regdate;
    }

    public void setRegular_manage_regdate(Date regular_manage_regdate) {
        this.regular_manage_regdate = regular_manage_regdate;
    }

    public Date getRegular_manage_update() {
        return regular_manage_update;
    }

    public void setRegular_manage_update(Date regular_manage_update) {
        this.regular_manage_update = regular_manage_update;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
