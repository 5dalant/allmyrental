package com.fivedalant.allmyrental.Service;

import net.minidev.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface UserService {

    public JSONObject login(HashMap<String, Object> prmMap)throws Exception;

    public JSONObject signUp(HashMap<String, Object> prmMap)throws Exception;

    public JSONObject inquiryId(HashMap<String, Object> prmMap)throws Exception;

    public JSONObject inquiryPw(HashMap<String, Object> prmMap)throws Exception;

    public JSONObject changePw(Long user_id, String password)throws Exception;

    public boolean checkAuth(HttpServletRequest request,Long user_id) throws Exception;

    public JSONObject seeUserDetail(Long user_id)throws Exception;

    public JSONObject changeUserSetting(Long user_id,int auto_login,int user_push,int user_terms)throws Exception;

    public JSONObject changeInfo(Long user_id,HashMap<String, Object> reqMap)throws Exception;
}
