package com.fivedalant.allmyrental.Controller;

import com.fivedalant.allmyrental.Service.AfterServiceService;
import com.fivedalant.allmyrental.Service.UserService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class AfterServiceController {

    @Autowired
    AfterServiceService afterServiceService;

    @Autowired
    UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //AS 등록
    @PostMapping(value = "/api/afterService/{user_id}/createAfterService")
    @ResponseBody
    public JSONObject createAfterService(@PathVariable("user_id") Long user_id, @RequestParam HashMap<String, Object> reqMap, @RequestParam(value = "files", required = false) List<MultipartFile> files) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = afterServiceService.createAfterService(user_id, reqMap, files);
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

    //AS 목록 보기
    @GetMapping(value = "/api/afterService/{user_id}/seeAfterService")
    @ResponseBody
    public JSONObject seeAfterService(@PathVariable("user_id") Long user_id, ServletRequest request) {

        JSONObject jsonObject = new JSONObject();

        try {
            if (userService.checkAuth((HttpServletRequest) request, user_id)) {
                jsonObject = afterServiceService.seeAfterService(user_id);
            } else {
                jsonObject.put("result", 2);
            }
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

    //AS 상세보기
    @GetMapping(value = "/api/afterService/{user_id}/seeAfterServiceDetail/{after_service_id}")
    @ResponseBody
    public JSONObject seeAfterServiceDetail(@PathVariable("user_id") Long user_id, @PathVariable("after_service_id") Long after_service_id, ServletRequest request) {

        JSONObject jsonObject = new JSONObject();

        try {
            if (userService.checkAuth((HttpServletRequest) request, user_id)) {
                jsonObject = afterServiceService.seeAfterServiceDetail(after_service_id);
            } else {
                jsonObject.put("result", 2);
            }
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

    @PostMapping(value = "/api/{user_id}/afterService/{after_service_id}/cancelAs")
    @ResponseBody
    public JSONObject cancelAs(@PathVariable("after_service_id") Long after_service_id, ServletRequest request, @PathVariable("user_id") Long user_id) {
        JSONObject jsonObject = new JSONObject();

        try {
            if (userService.checkAuth((HttpServletRequest) request, user_id)) {
                jsonObject = afterServiceService.cancelAs(after_service_id);
            } else {
                jsonObject.put("result", 2);
            }

        } catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }
}
