package com.fivedalant.allmyrental.Service;

import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import java.util.HashMap;

public interface MyRentalService {

    public JSONObject seeMyRentals(Long user_id,int Offset)throws Exception;

    public JSONObject createMyRental(Long user_id, HashMap<String, Object> reqMap, MultipartFile files)throws Exception;

    public JSONObject seeMyRentalDetail(Long rental_id)throws Exception;

    public JSONObject createRegularManage(Long user_id,HashMap<String, Object> reqMap)throws Exception;

    public JSONObject seeSelfManage(Long product_id)throws Exception;

    public JSONObject deleteContractImage(Long rental_id)throws Exception;

    public JSONObject uploadContractImage(Long rental_id,MultipartFile files,Long user_id)throws Exception;
}
