package com.fivedalant.allmyrental.Repository;

import com.fivedalant.allmyrental.Jpa.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Long> {

    @Query(value = "SELECT b.brand_id, b.brand_name,b.brand_regdate,b.brand_update,b.center_number FROM product a JOIN brand b on a.brand_id = b.brand_id WHERE product_name LIKE CONCAT('%',:searchWord,'%') GROUP BY brand_id",nativeQuery = true)
    List<Brand> searchBrand(String searchWord) throws Exception;
}
