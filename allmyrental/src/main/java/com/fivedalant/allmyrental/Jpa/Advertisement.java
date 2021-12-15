package com.fivedalant.allmyrental.Jpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="advertisement")
public class Advertisement {

    @Id
    @GeneratedValue
    private Long ad_id;                 // AD 아이디
    private String ad_name;             // AD 성명
    private String ad_email;            // AD 이메일
    private String ad_phone_number;     // AD 전화번호
    private String ad_company;          // AD 회사명

    @Temporal(TemporalType.TIMESTAMP)
    private Date ad_regdate;            // AD 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date ad_update;             // AD 수정일

    public Long getAd_id() {
        return ad_id;
    }

    public void setAd_id(Long ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_email() {
        return ad_email;
    }

    public void setAd_email(String ad_email) {
        this.ad_email = ad_email;
    }

    public String getAd_phone_number() {
        return ad_phone_number;
    }

    public void setAd_phone_number(String ad_phone_number) {
        this.ad_phone_number = ad_phone_number;
    }

    public String getAd_company() {
        return ad_company;
    }

    public void setAd_company(String ad_company) {
        this.ad_company = ad_company;
    }

    public Date getAd_regdate() {
        return ad_regdate;
    }

    public void setAd_regdate(Date ad_regdate) {
        this.ad_regdate = ad_regdate;
    }

    public Date getAd_update() {
        return ad_update;
    }

    public void setAd_update(Date ad_update) {
        this.ad_update = ad_update;
    }
}
