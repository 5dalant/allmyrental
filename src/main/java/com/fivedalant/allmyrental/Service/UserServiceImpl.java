package com.fivedalant.allmyrental.Service;

import com.fivedalant.allmyrental.Config.JwtTokenProvider;
import com.fivedalant.allmyrental.Jpa.User;
import com.fivedalant.allmyrental.Repository.RentalRepository;
import com.fivedalant.allmyrental.Repository.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //로그인
    @Override
    public JSONObject login(HashMap<String, Object> prmMap)throws Exception{

        JSONObject jsonObject = new JSONObject();
        User user = new User();

        String user_login_id = !String.valueOf(prmMap.get("user_login_id")).equals("") ? String.valueOf(prmMap.get("user_login_id")) : "";
        String password = !String.valueOf(prmMap.get("password")).equals("") ? String.valueOf(prmMap.get("password")) : "";

        if(!user_login_id.equals("")){
            user = userRepository.login(user_login_id);
            //사용자 있는지 체크
            if(user != null){
                //비밀번호 체크
                if(passwordEncoder.matches(password,user.getPassword())){
                    jsonObject.put("result",1); //결과 성공 여부
                    jsonObject.put("token",jwtTokenProvider.createToken(user_login_id)); //토큰 만들기
                    jsonObject.put("user_id",user.getUser_id()); // 유저 아이디
                    jsonObject.put("rentals",rentalRepository.userRentalCount(user.getUser_id()));
                    user.setLogin_date(new Date()); //로그인 날짜 갱신
                    userRepository.save(user);
                }else{
                    jsonObject.put("result",2);
                    jsonObject.put("token",null);
                }
            }else{
                jsonObject.put("result",2);
                jsonObject.put("token",null);
            }
        }
        return jsonObject;
    }

    //회원가입
    @Override
    public JSONObject signUp(HashMap<String, Object> prmMap)throws Exception{

        JSONObject jsonObject = new JSONObject();
        User user = new User();
        Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(prmMap.get("birth"))); //생년월일 데이터 포멧

        user.setUser_login_id(String.valueOf(prmMap.get("user_login_id")));                 //로그인 아이디
        user.setPassword(passwordEncoder.encode(String.valueOf(prmMap.get("password"))));   //비밀번호
        user.setUser_name(String.valueOf(prmMap.get("user_name")));                         //유저 이름
        user.setPhone_number(String.valueOf(prmMap.get("phone_number")));                   //핸드폰 번호
        user.setFavorite(String.valueOf(prmMap.get("favorite")));                           //관심사
        user.setConsider(String.valueOf(prmMap.get("consider")));                           //고려사항
        user.setGrade(Integer.parseInt(String.valueOf(prmMap.get("grade"))));               //유저 등급
        user.setUser_push(Integer.parseInt(String.valueOf(prmMap.get("user_push"))));       //유저 알람 여부
        user.setUser_terms(Integer.parseInt(String.valueOf(prmMap.get("user_terms"))));     //유저 권한 체크 여부
        user.setBirth(birth);                                                               //생년월일
        user.setUser_regdate(new Date());                                                   //유저 등록일
        user.setUser_update(new Date());                                                    //유저 수정일
        if(prmMap.get("push_token") != null){
            user.setPush_token(String.valueOf(prmMap.get("push_token")));
        }

        userRepository.save(user);

        jsonObject.put("result",1);
        return jsonObject;
    }

    @Override
    public JSONObject inquiryId(HashMap<String, Object> prmMap)throws Exception {

        JSONObject jsonObject = new JSONObject();

        String user_name = String.valueOf(prmMap.get("user_name"));
        String phone_number = String.valueOf(prmMap.get("phone_number"));
        Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(prmMap.get("birth"))); //생년월일 데이터 포멧

        String user_login_id = userRepository.inquiryId(user_name,phone_number,birth);

        if(user_login_id == null){
            jsonObject.put("result",2);
        }else{
            jsonObject.put("result",1);
            jsonObject.put("user_login_id",user_login_id);
        }

        return jsonObject;
    }

    //비밀번호 찾기
    @Override
    public JSONObject inquiryPw(HashMap<String, Object> prmMap)throws Exception{
        JSONObject jsonObject = new JSONObject();

        Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(prmMap.get("birth"))); //생년월일 데이터 포멧

        String login_user_id = String.valueOf(prmMap.get("user_login_id"));
        String user_name = String.valueOf(prmMap.get("user_name"));
        String phone_number = String.valueOf(prmMap.get("phone_number"));

        Long user_id = userRepository.inquiryPw(login_user_id,user_name,phone_number,birth);

        if(user_id == null){
            jsonObject.put("result",2);
        }else{
            jsonObject.put("result",1);
            jsonObject.put("user_id",user_id);
        }

        return jsonObject;
    }

    public JSONObject changePw(Long user_id, String password)throws Exception{

        JSONObject jsonObject = new JSONObject();

        User user = userRepository.seeUser(user_id);
        user.setPassword(passwordEncoder.encode(password));   //비밀번호
        userRepository.save(user);
        jsonObject.put("result",1);

        return jsonObject;
    }

    //권한 확인
    @Override
    public boolean checkAuth(HttpServletRequest request,Long user_id) throws Exception {

        boolean result = false;

        String token = jwtTokenProvider.resolveToken(request);        //HttpServletRequest에서 가져온 token 풀기

        if (token != null && jwtTokenProvider.validateToken(token)) { //토큰이 유효한지 검사
            String user_login_id = jwtTokenProvider.getUserPk(token); //유저 로그인 아이디

            User user = userRepository.login(user_login_id);          //로그인한 유저와 데이터 얻으려고 하는 유저가 맞는지 비교
            if(user.getUser_id().equals(user_id)){
                result = true;
            }
        }

        return result;
    }

    //유저 상세보기
    @Override
    public JSONObject seeUserDetail(Long user_id)throws Exception{

        JSONObject jsonObject = new JSONObject();

        //유저 디테일
        jsonObject.put("user",userRepository.seeUser(user_id));

        return jsonObject;
    }

    //유저 셋팅 변경
    @Override
    public JSONObject changeUserSetting(Long user_id,int auto_login,int user_push,int user_terms)throws Exception{

        JSONObject jsonObject = new JSONObject();

        User user = userRepository.seeUser(user_id);

        //유저가 있을 경우
        if(user != null){

            user.setAuto_login(auto_login); //자동 로그인
            user.setUser_push(user_push);   //유저 푸쉬 여부
            user.setUser_terms(user_terms); //유저 권한 여부
            userRepository.save(user);
            jsonObject.put("result",1);
        }else{
            jsonObject.put("result",2);
        }

        return jsonObject;
    }

    @Override
    public JSONObject changeInfo(Long user_id,HashMap<String, Object> reqMap)throws Exception{

        JSONObject jsonObject = new JSONObject();

        User user = userRepository.seeUser(user_id);

        String user_name = String.valueOf(reqMap.get("user_name"));
        String birthDay = String.valueOf(reqMap.get("birth"));
        String phone_number = String.valueOf(reqMap.get("phone_number"));

        if(!user_name.equals("null")){
            user.setUser_name(user_name);
        }

        if(!phone_number.equals("null")){
            user.setPhone_number(phone_number);
        }

        if(!birthDay.equals("null")){
            Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(reqMap.get("birth")));
            user.setBirth(birth);
        }

        userRepository.save(user);

        jsonObject.put("result",1);
        return jsonObject;
    }

}
