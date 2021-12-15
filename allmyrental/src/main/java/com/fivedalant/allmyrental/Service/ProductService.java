package com.fivedalant.allmyrental.Service;

import net.minidev.json.JSONObject;

public interface ProductService {

    public JSONObject searchMainProduct() throws Exception;

    public JSONObject seeProductDetail(Long product_id)throws Exception;
}
