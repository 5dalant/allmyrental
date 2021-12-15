package com.fivedalant.allmyrental.Jpa;

import javax.persistence.*;
import java.util.Date;

//검색
@Entity
@Table(name="search")
public class Search {

    @Id
    @GeneratedValue
    private Long search_id;         //검색 아이디
    private String search_name;     //검색 이름
    private Long search_hits;       //검색 조회 수

    @Temporal(TemporalType.TIMESTAMP)
    private Date search_regdate;    // 검색 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date search_update;     // 검색 수정일

    public Long getSearch_id() {
        return search_id;
    }

    public void setSearch_id(Long search_id) {
        this.search_id = search_id;
    }

    public String getSearch_name() {
        return search_name;
    }

    public void setSearch_name(String search_name) {
        this.search_name = search_name;
    }

    public Long getSearch_hits() {
        return search_hits;
    }

    public void setSearch_hits(Long search_hits) {
        this.search_hits = search_hits;
    }

    public Date getSearch_regdate() {
        return search_regdate;
    }

    public void setSearch_regdate(Date search_regdate) {
        this.search_regdate = search_regdate;
    }

    public Date getSearch_update() {
        return search_update;
    }

    public void setSearch_update(Date search_update) {
        this.search_update = search_update;
    }
}
