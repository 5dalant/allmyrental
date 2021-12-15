package com.fivedalant.allmyrental.Jpa;

import javax.persistence.*;
import java.util.Date;

//브랜드
@Entity
@Table(name="brand")
public class Brand {

    @Id
    @GeneratedValue
    private Long brand_id;      //브랜드 id
    private String brand_name;  //브랜드 이름
    private String center_number; //브랜드 고객센터 전화번호

    @Temporal(TemporalType.TIMESTAMP)
    private Date brand_regdate; // 브랜드 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date brand_update; // 브랜드 수정일

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public Date getBrand_regdate() {
        return brand_regdate;
    }

    public void setBrand_regdate(Date brand_regdate) {
        this.brand_regdate = brand_regdate;
    }

    public Date getBrand_update() {
        return brand_update;
    }

    public void setBrand_update(Date brand_update) {
        this.brand_update = brand_update;
    }

    public String getCenter_number() {
        return center_number;
    }

    public void setCenter_number(String center_number) {
        this.center_number = center_number;
    }
}
