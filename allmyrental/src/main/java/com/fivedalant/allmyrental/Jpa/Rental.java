package com.fivedalant.allmyrental.Jpa;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

//렌탈
@Entity
@Table(name="rental")
public class Rental {

    @Id
    @GeneratedValue
    private Long rental_id; //렌탈 아이디
    private String rental_user_name; //렌탈 유저 이름
    private String rental_phone_number; //렌탈 전화번호
    private String address; //렌탈 주소
    private Long rental_cost; //렌탈 비용
    private String pay_method; //렌탈 지불 방법
    private String card_number; //렌탈 지불 카드번호
    private String obligatory_period; //렌탈 의무기간
    private String contact_image; //렌탈 이미지

    @Temporal(TemporalType.DATE)
    private Date contact_start_date; //렌탈 계약 시작일

    @Temporal(TemporalType.DATE)
    private Date contact_last_date; //렌탈 계약 종료일

    @Temporal(TemporalType.TIMESTAMP)
    private Date rental_regdate; //렌탈 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date rental_update; //렌탈 수정일

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regular_manage_id")
    private RegularManage regularManage;

    public RegularManage getRegularManage() {
        return regularManage;
    }

    public void setRegularManage(RegularManage regularManage) {
        this.regularManage = regularManage;
    }

    public Long getRental_id() {
        return rental_id;
    }

    public void setRental_id(Long rental_id) {
        this.rental_id = rental_id;
    }

    public String getRental_user_name() {
        return rental_user_name;
    }

    public void setRental_user_name(String rental_user_name) {
        this.rental_user_name = rental_user_name;
    }

    public String getRental_phone_number() {
        return rental_phone_number;
    }

    public void setRental_phone_number(String rental_phone_number) {
        this.rental_phone_number = rental_phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getRental_cost() {
        return rental_cost;
    }

    public void setRental_cost(Long rental_cost) {
        this.rental_cost = rental_cost;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getObligatory_period() {
        return obligatory_period;
    }

    public void setObligatory_period(String obligatory_period) {
        this.obligatory_period = obligatory_period;
    }

    public String getContact_image() {
        return contact_image;
    }

    public void setContact_image(String contact_image) {
        this.contact_image = contact_image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getContact_start_date() {
        return contact_start_date;
    }

    public void setContact_start_date(Date contact_start_date) {
        this.contact_start_date = contact_start_date;
    }

    public Date getContact_last_date() {
        return contact_last_date;
    }

    public void setContact_last_date(Date contact_last_date) {
        this.contact_last_date = contact_last_date;
    }

    public Date getRental_regdate() {
        return rental_regdate;
    }

    public void setRental_regdate(Date rental_regdate) {
        this.rental_regdate = rental_regdate;
    }

    public Date getRental_update() {
        return rental_update;
    }

    public void setRental_update(Date rental_update) {
        this.rental_update = rental_update;
    }
}
