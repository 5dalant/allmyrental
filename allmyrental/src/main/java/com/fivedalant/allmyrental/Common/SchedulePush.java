package com.fivedalant.allmyrental.Common;

import com.fivedalant.allmyrental.DTO.ScheduleDto;
import com.fivedalant.allmyrental.Jpa.User;
import com.fivedalant.allmyrental.Repository.RentalRepository;
import com.fivedalant.allmyrental.Repository.UserRepository;
import lombok.extern.java.Log;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@Log
public class SchedulePush {

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    UserRepository userRepository;

    private static RestTemplate restTemplate;

    @Scheduled(cron = "30 8 18 * * *")
    public void PushTasks() throws Exception {

        List<ScheduleDto> scheduleDtos = rentalRepository.seeSchedule();

        if(scheduleDtos != null){
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type","application/json; charset=utf-8");
            restTemplate = new RestTemplate();
            headers = new HttpHeaders();
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            for(ScheduleDto scheduleDto : scheduleDtos){
                Long user_id = scheduleDto.getUser_id();
                int count_contract = scheduleDto.getCount_contract();

                User user = userRepository.seeUser(user_id);
                if(user.getUser_push() == 1){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("to","ExponentPushToken["+user.getPush_token()+"]");
                    jsonObject.put("title","올마이 렌탈");
                    jsonObject.put("body",user.getUser_name()+"님 만료예정인 렌탈기기가 "+ count_contract+"개 있습니다.");
                    jsonObject.put("badge",1);
                    HttpEntity<String> request =
                            new HttpEntity<String>(jsonObject.toString(), headers);

                    String url = "https://exp.host/--/api/v2/push/send";
                    String response = restTemplate.postForObject(url, request ,String.class );
                }
            }
        }
    }
}
