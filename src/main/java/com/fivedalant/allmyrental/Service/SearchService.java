package com.fivedalant.allmyrental.Service;

import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

public interface SearchService {

    public JSONObject seeMainProduct() throws Exception;

    public JSONObject seeSearchProduct(String searchWord, String order_category, int offset,Long category_id,Long brand_id) throws Exception;

    public JSONObject seeMoreProduct(String searchWord,String order_category,int offset) throws Exception;

    public JSONObject seeProductDetail(Long product_id)throws Exception;
}
