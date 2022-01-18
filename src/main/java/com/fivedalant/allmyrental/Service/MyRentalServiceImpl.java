package com.fivedalant.allmyrental.Service;

import com.fivedalant.allmyrental.Common.FileUploadService;
import com.fivedalant.allmyrental.Jpa.Product;
import com.fivedalant.allmyrental.Jpa.RegularManage;
import com.fivedalant.allmyrental.Jpa.Rental;
import com.fivedalant.allmyrental.Jpa.User;
import com.fivedalant.allmyrental.Repository.ProductRepository;
import com.fivedalant.allmyrental.Repository.RegularManageRepository;
import com.fivedalant.allmyrental.Repository.RentalRepository;
import com.fivedalant.allmyrental.Repository.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service("myRentalService")
public class MyRentalServiceImpl implements MyRentalService {

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegularManageRepository regularManageRepository;

    @Autowired
    FileUploadService fileUploadService;

    //렌탈 목록 가져오기
    @Override
    public JSONObject seeMyRentals(Long user_id, int offset) throws Exception {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("user", userRepository.seeUser(user_id));
        jsonObject.put("rentals", rentalRepository.seeMyRentals(user_id, offset));
        jsonObject.put("rentalCount",rentalRepository.countMyRentals(user_id));

        return jsonObject;
    }

    //렌탈 제품 등록
    @Override
    public JSONObject createMyRental(Long user_id, HashMap<String, Object> reqMap, MultipartFile files) throws Exception {

        JSONObject jsonObject = new JSONObject();

        Rental rental = new Rental();

        Date contact_start_date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(reqMap.get("contact_start_date"))); //계약 시작일
        Date contact_last_date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(reqMap.get("contact_last_date"))); // 계약 종료일
        Date today = new Date();

        rental.setRental_user_name(String.valueOf(reqMap.get("rental_user_name")));
        rental.setRental_phone_number(String.valueOf(reqMap.get("rental_phone_number")));
        rental.setAddress(String.valueOf(reqMap.get("address")));
        rental.setRental_cost(Long.parseLong(String.valueOf(reqMap.get("rental_cost"))));
        rental.setPay_method(String.valueOf(reqMap.get("pay_method")));
        rental.setCard_number(String.valueOf(reqMap.get("card_number")));
        rental.setObligatory_period(String.valueOf(reqMap.get("obligatory_period")));
        rental.setContact_start_date(contact_start_date);
        rental.setContact_last_date(contact_last_date);
        rental.setRental_regdate(today);
        rental.setRental_update(today);

        Product product = productRepository.seeProductDetail(Long.parseLong(String.valueOf(reqMap.get("product_id"))));
        User user = userRepository.seeUser(user_id);

        rental.setProduct(product);
        rental.setUser(user);

        Rental upload_rental = rentalRepository.save(rental);

        if (files != null) {
            String fileName = files.getName();
            Date date = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
            String time = format1.format(date);
            fileName = "contract/" + user_id + "/" + upload_rental.getRental_id() + "_" + time;
            String fileResult = fileUploadService.uploadImage(files, fileName);
            upload_rental.setContact_image(fileResult);
            rentalRepository.save(upload_rental);
        }

        jsonObject.put("result", 1);
        jsonObject.put("rental", upload_rental);

        return jsonObject;
    }

    //마이렌탈 상세보기
    public JSONObject seeMyRentalDetail(Long rental_id) throws Exception {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("rental", rentalRepository.seeMyRentalDetail(rental_id));

        return jsonObject;
    }

    //정기 관리 신청
    @Override
    public JSONObject createRegularManage(Long user_id, HashMap<String, Object> reqMap) throws Exception {

        JSONObject jsonObject = new JSONObject();

        RegularManage regularManage;

        Date today = new Date();

        if (Objects.equals(String.valueOf(reqMap.get("regular_manage_id")), "0")) {
            regularManage = new RegularManage(); //정기관리 렌탈
        } else {
            regularManage = regularManageRepository.seeRegularManage(Long.parseLong(String.valueOf(reqMap.get("regular_manage_id"))));
        }
        regularManage.setUser(userRepository.seeUser(user_id));                                                                            //유저
        regularManage.setRegular_manage_date(Integer.parseInt(String.valueOf(reqMap.get("regular_manage_date"))));                         //정기관리 방문일
        regularManage.setRegular_manage_notification(Integer.parseInt(String.valueOf(reqMap.get("regular_manage_notification"))));           //정기관리 알람 여부
        regularManage.setRegular_manage_notification_date(Integer.parseInt(String.valueOf(reqMap.get("regular_manage_notification_date")))); //정기관리 알람 날짜
        regularManage.setRegular_manage_regdate(today);                                                                                    //정기관리 등록일
        regularManage.setRegular_manage_update(today);                                                                                     //정기관리 수정일
        regularManage.setRental(rentalRepository.seeMyRentalDetail(Long.parseLong(String.valueOf(reqMap.get("rental_id")))));

        regularManageRepository.save(regularManage);

        jsonObject.put("result", 1);
        return jsonObject;
    }

    //셀프 관리 방법
    @Override
    public JSONObject seeSelfManage(Long product_id) throws Exception {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("product", productRepository.seeProductDetail(product_id));

        jsonObject.put("result", 1);

        return jsonObject;
    }

    //계약서 사진 삭제
    @Override
    public JSONObject deleteContractImage(Long rental_id)throws Exception{
        JSONObject jsonObject = new JSONObject();

        Rental rental = new Rental();
        rental = rentalRepository.seeMyRentalDetail(rental_id);
        rental.setContact_image("");
        rentalRepository.save(rental);

        jsonObject.put("result",1);

        return jsonObject;
    }

    //계약서 사진 업로드
    @Override
    public JSONObject uploadContractImage(Long rental_id,MultipartFile files,Long user_id)throws Exception{

        JSONObject jsonObject = new JSONObject();

        Rental rental = new Rental();
        rental = rentalRepository.seeMyRentalDetail(rental_id);

        if (files != null) {
            String fileName = files.getName();
            Date date = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
            String time = format1.format(date);
            fileName = "contract/" + user_id + "/" + rental_id + "_" + time;
            String fileResult = fileUploadService.uploadImage(files, fileName);
            rental.setContact_image(fileResult);
            rentalRepository.save(rental);
            jsonObject.put("result",1);
        }else{
            jsonObject.put("result",2);
        }

        return jsonObject;
    }
}
