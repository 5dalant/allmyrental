package com.fivedalant.allmyrental.Jpa;

import javax.persistence.*;
import java.util.Date;

//상품
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue
    private Long product_id;                        //상품 아이디
    private String detail_lists;                    //상품 상세보기 항목
    private String detail_contents;                 //상품 상세보기 내용
    private String product_name;                    //상품 이름
    private String product_code;                    //상품 코드
    private String product_color;                   //상품 컬러
    private String represent_lists;                 //상품 대표 항목
    private String represent_contents;              //상품 대표 내용
    private String represent_image;                 //상품 대표 이미지
    private String thumbnail_image;                 //썸네일 이미지
    private Long product_hits;                      //상품 조회수
    private String product_self_manage_contents;    //상품 자가 관리 내용
    private String product_self_manage_image;       //상품 자가 관리 이미지
    private int product_advertisement = 0;          //상품 광고 여부
    private String search_word;                     //검색어

    @Column(length = 1000)
    private String purchase_url;                    //구입 url

    public int getProduct_advertisement() {
        return product_advertisement;
    }

    public void setProduct_advertisement(int product_advertisement) {
        this.product_advertisement = product_advertisement;
    }

    @Temporal(TemporalType.DATE)
    private Date launch_date;                       // 출시일

    @Temporal(TemporalType.TIMESTAMP)
    private Date product_regdate;                   // 상품 등록일

    @Temporal(TemporalType.TIMESTAMP)
    private Date product_update;                    // 상품 수정일

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;                      //카테고리 아이디

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;                            //브랜드 아이디

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDetail_lists() {
        return detail_lists;
    }

    public void setDetail_lists(String detail_lists) {
        this.detail_lists = detail_lists;
    }

    public String getDetail_contents() {
        return detail_contents;
    }

    public void setDetail_contents(String detail_contents) {
        this.detail_contents = detail_contents;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_color() {
        return product_color;
    }

    public void setProduct_color(String product_color) {
        this.product_color = product_color;
    }

    public String getRepresent_lists() {
        return represent_lists;
    }

    public void setRepresent_lists(String represent_lists) {
        this.represent_lists = represent_lists;
    }

    public String getRepresent_contents() {
        return represent_contents;
    }

    public void setRepresent_contents(String represent_contents) {
        this.represent_contents = represent_contents;
    }

    public Long getProduct_hits() {
        return product_hits;
    }

    public void setProduct_hits(Long product_hits) {
        this.product_hits = product_hits;
    }

    public String getProduct_self_manage_contents() {
        return product_self_manage_contents;
    }

    public void setProduct_self_manage_contents(String product_self_manage_contents) {
        this.product_self_manage_contents = product_self_manage_contents;
    }

    public String getProduct_self_manage_image() {
        return product_self_manage_image;
    }

    public void setProduct_self_manage_image(String product_self_manage_image) {
        this.product_self_manage_image = product_self_manage_image;
    }

    public Date getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
    }

    public Date getProduct_regdate() {
        return product_regdate;
    }

    public void setProduct_regdate(Date product_regdate) {
        this.product_regdate = product_regdate;
    }

    public Date getProduct_update() {
        return product_update;
    }

    public void setProduct_update(Date product_update) {
        this.product_update = product_update;
    }

    public String getRepresent_image() {
        return represent_image;
    }

    public void setRepresent_image(String represent_image) {
        this.represent_image = represent_image;
    }

    public String getThumbnail_image() {
        return thumbnail_image;
    }

    public void setThumbnail_image(String thumbnail_image) {
        this.thumbnail_image = thumbnail_image;
    }

    public String getSearch_word() {
        return search_word;
    }

    public void setSearch_word(String search_word) {
        this.search_word = search_word;
    }

    public String getPurchase_url() {
        return purchase_url;
    }

    public void setPurchase_url(String purchase_url) {
        this.purchase_url = purchase_url;
    }
}
