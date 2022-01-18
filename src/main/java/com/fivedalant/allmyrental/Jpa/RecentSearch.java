package com.fivedalant.allmyrental.Jpa;

import javax.persistence.*;
import java.util.Date;

//최근 검색
@Entity
@Table(name="recent_search")
public class RecentSearch {

    @Id
    @GeneratedValue
    private Long recent_search_id; //최근 검색 아이디
    private String recent_search_name; //최근 검색어

    @Temporal(TemporalType.TIMESTAMP)
    private Date recent_search_regdate; //최근 검색 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date recent_search_update; //최근 검색 수정일

    @ManyToOne
    @JoinColumn(name="user_id",insertable = false,updatable = false)
    private User user;

    public void setRecent_search_id(Long recent_search_id) {
        this.recent_search_id = recent_search_id;
    }

    public String getRecent_search_name() {
        return recent_search_name;
    }

    public void setRecent_search_name(String recent_search_name) {
        this.recent_search_name = recent_search_name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRecent_search_regdate() {
        return recent_search_regdate;
    }

    public void setRecent_search_regdate(Date recent_search_regdate) {
        this.recent_search_regdate = recent_search_regdate;
    }

    public Date getRecent_search_update() {
        return recent_search_update;
    }

    public void setRecent_search_update(Date recent_search_update) {
        this.recent_search_update = recent_search_update;
    }
}
