package com.fivedalant.allmyrental.Service;

import com.fivedalant.allmyrental.Jpa.Product;
import com.fivedalant.allmyrental.Repository.ProductRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public JSONObject searchMainProduct() throws Exception{

        JSONObject jsonObject = new JSONObject();

        List<Product> products = productRepository.product();

        //product 추가
        /*product.setProduct_brand("쿠쿠");
        product.setProduct_category(2);
        product.setDetail_lists("먼지제거등급(세미헤파/헤파/울파)|숯탈취필터(활성탄)필터");
        product.setDetail_contents("헤파|숯탈취");
        product.setProduct_name("미세먼지청정 울트라12000공기청정기(셀프관리)");
        product.setProduct_code("AC-34U10FWS");
        product.setProduct_color("6c5ce7|b2bec3");
        product.setRepresent_lists("미세먼지제거|탈취기능");
        product.setRepresent_contents("1|0");
        product.setProduct_hits(0L);
        product.setProduct_regdate(new Date());
        product.setProduct_update(new Date());
        product.setLaunch_date(new Date());*/

        //productRepository.save(product);

        jsonObject.put("products",products);

        return jsonObject;
    }

    //제품 상세보기
    @Override
    public JSONObject seeProductDetail(Long product_id)throws Exception{

        JSONObject jsonObject = new JSONObject();

        Product product = productRepository.seeProductDetail(product_id);

        if(product != null){
            jsonObject.put("result",1);
            jsonObject.put("product",product);
        }else{
            jsonObject.put("result",2);
        }

        return jsonObject;
    }
}
