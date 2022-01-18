package com.fivedalant.allmyrental.Controller;

import com.fivedalant.allmyrental.Service.AdvertisementService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class AdvertisementController {

    @Autowired
    AdvertisementService advertisementService;

    //광고 등록
    @PostMapping(value = "/api/search/createAdvertisement")
    @ResponseBody
    public JSONObject createAdvertisement(@RequestParam HashMap<String, Object> reqMap){

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = advertisementService.createAdvertisement(reqMap);
        }catch (Exception e){
            jsonObject.put("result",3);
        }

        return jsonObject;
    }

    //광고 등록
    @GetMapping(value = "")
    @ResponseBody
    public JSONObject Home(){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("result","this page good");

        return jsonObject;
    }
}
