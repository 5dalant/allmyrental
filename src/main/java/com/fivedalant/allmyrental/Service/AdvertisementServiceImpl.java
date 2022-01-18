package com.fivedalant.allmyrental.Service;

import com.fivedalant.allmyrental.Jpa.Advertisement;
import com.fivedalant.allmyrental.Repository.AdvertisementRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;

@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService{

    @Autowired
    AdvertisementRepository advertisementRepository;

    //광고 등록
    @Override
    public JSONObject createAdvertisement(@RequestParam HashMap<String, Object> reqMap) throws Exception{

        JSONObject jsonObject = new JSONObject();

        Advertisement advertisement = new Advertisement();

        Date today = new Date();

        //광고 데이터 넣기
        advertisement.setAd_name(String.valueOf(reqMap.get("ad_name")));                    //광고 신청 유저 이름
        advertisement.setAd_email(String.valueOf(reqMap.get("ad_email")));                  //광고 신청 이메일
        advertisement.setAd_phone_number(String.valueOf(reqMap.get("ad_phone_number")));    //광고 신청 핸드폰 번호
        advertisement.setAd_company(String.valueOf(reqMap.get("ad_company")));              //광고 신청 회사
        advertisement.setAd_regdate(today);
        advertisement.setAd_update(today);

        //광고 데이터 insert
        advertisementRepository.save(advertisement);

        jsonObject.put("result",1);

        return jsonObject;
    }
}
