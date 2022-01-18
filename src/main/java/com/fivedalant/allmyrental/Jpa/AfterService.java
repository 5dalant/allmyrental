package com.fivedalant.allmyrental.Jpa;

import javax.persistence.*;
import java.util.Date;

// A/S
@Entity
@Table(name="after_service")
public class AfterService {

    @Id
    @GeneratedValue
    private Long after_service_id;              // AS 아이디
    private String after_service_content;       // AS 내용
    private String after_service_phone_number;  // AS 전화번호
    private String after_service_image;         // AS 첨부 이미지
    private String after_service_image_second;  // AS 두번째 이미지
    private int after_service_state;            // AS 상태
    private String after_service_helper;         // AS 기사님

    @Temporal(TemporalType.TIMESTAMP)
    private Date after_service_regdate;         // AS 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date after_service_update;          // AS 수정일

    @Temporal(TemporalType.DATE)
    private Date after_service_hope_date;       // AS 날짜

    @Temporal(TemporalType.DATE)
    private Date after_service_date;            // AS 날짜

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")              //제품 아이디
    private Product product;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")                 //유저 아아디
    private User user;

    @ManyToOne(targetEntity = Rental.class, fetch = FetchType.LAZY)
    @JoinColumn(name="rental_id")
    private Rental rental;

    public String getAfter_service_helper() {
        return after_service_helper;
    }

    public void setAfter_service_helper(String after_service_helper) {
        this.after_service_helper = after_service_helper;
    }

    public Long getAfter_service_id() {
        return after_service_id;
    }

    public void setAfter_service_id(Long after_service_id) {
        this.after_service_id = after_service_id;
    }

    public String getAfter_service_content() {
        return after_service_content;
    }

    public void setAfter_service_content(String after_service_content) {
        this.after_service_content = after_service_content;
    }

    public String getAfter_service_phone_number() {
        return after_service_phone_number;
    }

    public void setAfter_service_phone_number(String after_service_phone_number) {
        this.after_service_phone_number = after_service_phone_number;
    }

    public String getAfter_service_image() {
        return after_service_image;
    }

    public void setAfter_service_image(String after_service_image) {
        this.after_service_image = after_service_image;
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

    public Date getAfter_service_regdate() {
        return after_service_regdate;
    }

    public void setAfter_service_regdate(Date after_service_regdate) {
        this.after_service_regdate = after_service_regdate;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public Date getAfter_service_update() {
        return after_service_update;
    }

    public void setAfter_service_update(Date after_service_update) {
        this.after_service_update = after_service_update;
    }

    public Date getAfter_service_date() {
        return after_service_date;
    }

    public void setAfter_service_date(Date after_service_date) {
        this.after_service_date = after_service_date;
    }

    public int getAfter_service_state() {
        return after_service_state;
    }

    public void setAfter_service_state(int after_service_state) {
        this.after_service_state = after_service_state;
    }

    public Date getAfter_service_hope_date() {
        return after_service_hope_date;
    }

    public void setAfter_service_hope_date(Date after_service_hope_date) {
        this.after_service_hope_date = after_service_hope_date;
    }

    public String getAfter_service_image_second() {
        return after_service_image_second;
    }

    public void setAfter_service_image_second(String after_service_image_second) {
        this.after_service_image_second = after_service_image_second;
    }
}
