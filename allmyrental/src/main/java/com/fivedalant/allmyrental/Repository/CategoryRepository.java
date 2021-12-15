package com.fivedalant.allmyrental.Repository;

import com.fivedalant.allmyrental.Jpa.Category;
import com.fivedalant.allmyrental.Jpa.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    //제품 상세보기
    @Query(value = "SELECT * FROM category WHERE category_name = :category_name",nativeQuery = true)
    Category seeCategory(String category_name) throws Exception;

    @Query(value = "SELECT b.category_id, b.category_name,b.category_regdate,b.category_update FROM product a JOIN category b on a.category_id = b.category_id WHERE product_name LIKE CONCAT('%',:searchWord,'%') GROUP BY category_id",nativeQuery = true)
    List<Category> searchCategory(String searchWord) throws Exception;
}
