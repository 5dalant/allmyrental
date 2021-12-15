package com.fivedalant.allmyrental.Service;

import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

public interface AdvertisementService {

    public JSONObject createAdvertisement(@RequestParam HashMap<String, Object> reqMap) throws Exception;
}
