package com.fivedalant.allmyrental.Repository;

import com.fivedalant.allmyrental.Jpa.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //검색 메인페이지 제품 SELECT
    @Query(value = "SELECT * FROM product order by RAND() limit 6",nativeQuery = true)
    List<Product> product() throws Exception;

    //제품 검색 결과
    @Query(value = "SELECT * FROM product WHERE search_word LIKE CONCAT('%',:searchWord,'%') ORDER BY :order_category DESC LIMIT :offset, 10",nativeQuery = true)
    List<Product> seeSearchProduct(String searchWord,String order_category,int offset) throws Exception;

    //제품 광고 결과
    @Query(value = "SELECT * FROM product WHERE product_advertisement = 1 ORDER BY rand() LIMIT 0,5",nativeQuery = true)
    List<Product> seeAdvertisementProduct(String searchWord) throws Exception;

    //제품 상세보기
    @Query(value = "SELECT * FROM product WHERE product_id = :product_id",nativeQuery = true)
    Product seeProductDetail(Long product_id) throws Exception;

    //추천 제품 목록
    @Query(value = "SELECT * FROM product WHERE category_id = :category_id ORDER BY rand() LIMIT 0,3",nativeQuery = true)
    List<Product> seeRecommendProduct(Long category_id)throws Exception;

    //상품이름 가져오기
    @Query(value = "SELECT * FROM product WHERE search_word LIKE CONCAT('%',:searchWord,'%')",nativeQuery = true)
    List<Product> searchProductName(String searchWord)throws Exception;

    @Query(value = "SELECT * FROM product WHERE search_word LIKE CONCAT('%',:searchWord,'%') AND category_id = :category_id ORDER BY :order_category DESC LIMIT :offset, 10",nativeQuery = true)
    List<Product> seeRecommendSearchCategory(String searchWord,String order_category,int offset,Long category_id) throws Exception;

    @Query(value = "SELECT * FROM product WHERE search_word LIKE CONCAT('%',:searchWord,'%') AND brand_id = :brand_id ORDER BY :order_category DESC LIMIT :offset, 10",nativeQuery = true)
    List<Product> seeRecommendSearchBrand(String searchWord,String order_category,int offset,Long brand_id) throws Exception;

    @Query(value = "SELECT * FROM product WHERE search_word LIKE CONCAT('%',:searchWord,'%') AND brand_id = :brand_id AND category_id = :category_id ORDER BY :order_category DESC LIMIT :offset, 10",nativeQuery = true)
    List<Product> seeRecommendSearchBoth(String searchWord,String order_category,int offset,Long brand_id,Long category_id) throws Exception;
}
