package com.fivedalant.allmyrental.Controller;

import com.fivedalant.allmyrental.Repository.UserRepository;
import com.fivedalant.allmyrental.Service.UserService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    //로그인
    @PostMapping(value = "/api/user/login")
    @ResponseBody
    public JSONObject login(@RequestParam("user_login_id") String user_login_id,@RequestParam("password") String password){

        JSONObject jsonObject = new JSONObject();

        HashMap<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("user_login_id",user_login_id);
        prmMap.put("password",password);

        try{
            jsonObject = userService.login(prmMap);
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result",3);
        }

        return jsonObject;
    }

    //아이디 중복 확인
    @PostMapping(value = "/api/user/idCheck")
    @ResponseBody
    public JSONObject idCheck(@RequestParam("user_login_id") String user_login_id){

        JSONObject jsonObject = new JSONObject();

        try{
            int result = userRepository.idCheck(user_login_id);

            //아이디가 1개 이상일 경우 중복
            if(result > 0){
                jsonObject.put("result",2);
            }else{
                jsonObject.put("result",1);
            }

        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result",3);
        }

        return jsonObject;
    }

    //회원 가입
    @PostMapping(value = "/api/user/signUp")
    @ResponseBody
    public JSONObject signUp(@RequestParam HashMap<String, Object> reqMap){

        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject = userService.signUp(reqMap);
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result",2);
        }
        return jsonObject;
    }

    //아이디 찾기
    @PostMapping(value = "/api/user/inquiryId")
    @ResponseBody
    public JSONObject inquiryId(@RequestParam HashMap<String, Object> reqMap){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = userService.inquiryId(reqMap);
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result",3);
        }

        return jsonObject;
    }

    //비밀번호 찾기
    @PostMapping(value = "/api/user/inquiryPw")
    @ResponseBody
    public JSONObject inquiryPw(@RequestParam HashMap<String, Object> reqMap){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = userService.inquiryPw(reqMap);
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result",3);
        }

        return jsonObject;
    }

    //비밀번호 변경
    @PostMapping(value = "/api/user/{user_id}/changePw")
    @ResponseBody
    public JSONObject changePw(@PathVariable("user_id")Long user_id,@RequestParam("password")String password){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = userService.changePw(user_id,password);
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result",3);
        }

        return jsonObject;
    }

    //환경 설정 보기
    @GetMapping(value = "/api/user/{user_id}/seeUserDetail")
    @ResponseBody
    public JSONObject seeUserDetail(@PathVariable("user_id")Long user_id, ServletRequest request){

        JSONObject jsonObject = new JSONObject();

        try{
            //유저 권한 체크
            if(userService.checkAuth((HttpServletRequest) request, user_id)){
                jsonObject = userService.seeUserDetail(user_id);
            }else{
                jsonObject.put("result",2);
            }
        }catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result",3);
        }

        return jsonObject;
    }

    //유저 정보 변경
    @PostMapping(value = "/api/user/{user_id}/changeInfo")
    @ResponseBody
    public JSONObject changeInfo(@PathVariable("user_id")Long user_id, ServletRequest request,@RequestParam HashMap<String, Object> reqMap){

        JSONObject jsonObject = new JSONObject();

        try {
            //유저 권한 체크
            if(userService.checkAuth((HttpServletRequest) request, user_id)) {
                jsonObject = userService.changeInfo(user_id,reqMap);
            }else{
                jsonObject.put("result",2);
            }
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result",3);
        }

        return jsonObject;
    }


    //유저 셋팅 변경
    @PostMapping(value = "/user/{user_id}/changeUserSetting")
    @ResponseBody
    public JSONObject changeAutoLogin(@PathVariable("user_id")Long user_id,ServletRequest request,@RequestParam("auto_login") int auto_login,@RequestParam("user_push") int user_push,@RequestParam("user_terms") int user_terms){

        JSONObject jsonObject = new JSONObject();

        try {
            //유저 권한 체크
            if(userService.checkAuth((HttpServletRequest) request, user_id)){
                jsonObject = userService.changeUserSetting(user_id,auto_login,user_push,user_terms);
            }else{
                jsonObject.put("result",2);
            }
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result",3);
        }

        return jsonObject;
    }
}
