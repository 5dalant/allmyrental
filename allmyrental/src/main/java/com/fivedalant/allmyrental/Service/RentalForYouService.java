package com.fivedalant.allmyrental.Service;

import net.minidev.json.JSONObject;

public interface RentalForYouService {

    public JSONObject seeRecommendProduct(Long user_id)throws Exception;
}
