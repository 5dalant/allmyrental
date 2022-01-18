package com.fivedalant.allmyrental.Controller;

import com.fivedalant.allmyrental.Service.RentalForYouService;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalForYouController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RentalForYouService rentalForYouService;

    //제품 추천 목록
    @GetMapping(value = "/rentalforyou/{user_id}/seeRecommendProduct")
    @ResponseBody
    public JSONObject seeRecommendProduct(@PathVariable("user_id")Long user_id){

        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject = rentalForYouService.seeRecommendProduct(user_id);
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result",3);
        }

        return jsonObject;
    }
}
