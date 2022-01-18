package com.fivedalant.allmyrental.Jpa;

import javax.persistence.*;
import java.util.Date;

//카테고리
@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue
    private Long category_id;       // 카테고리 아이디
    private String category_name;   // 카테고리 이름

    @Temporal(TemporalType.TIMESTAMP)
    private Date category_regdate;  // 카테고리 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date category_update;   // 카테고리 수정일

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Date getCategory_regdate() {
        return category_regdate;
    }

    public void setCategory_regdate(Date category_regdate) {
        this.category_regdate = category_regdate;
    }

    public Date getCategory_update() {
        return category_update;
    }

    public void setCategory_update(Date category_update) {
        this.category_update = category_update;
    }
}
