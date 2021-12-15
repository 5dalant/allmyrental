package com.fivedalant.allmyrental.Service;

import com.fivedalant.allmyrental.Common.FileUploadService;
import com.fivedalant.allmyrental.Jpa.AfterService;
import com.fivedalant.allmyrental.Repository.AfterServiceRepository;
import com.fivedalant.allmyrental.Repository.ProductRepository;
import com.fivedalant.allmyrental.Repository.RentalRepository;
import com.fivedalant.allmyrental.Repository.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("afterServiceService")
public class AfterServiceServiceImpl implements AfterServiceService{

    @Autowired
    AfterServiceRepository afterServiceRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    FileUploadService fileUploadService;

    //AS 등록
    @Override
    public JSONObject createAfterService(Long user_id, @RequestParam HashMap<String, Object> reqMap, List<MultipartFile> files)throws Exception{

        JSONObject jsonObject = new JSONObject();

        AfterService afterService = new AfterService();

        Date today = new Date();

        Date after_service_hope_date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(reqMap.get("after_service_hope_date")));

        afterService.setAfter_service_content(String.valueOf(reqMap.get("after_service_content")));
        afterService.setAfter_service_state(Integer.parseInt(String.valueOf(reqMap.get("after_service_state"))));
        afterService.setAfter_service_regdate(today);
        afterService.setAfter_service_update(today);
        afterService.setAfter_service_hope_date(after_service_hope_date);
        afterService.setProduct(productRepository.seeProductDetail(Long.parseLong(String.valueOf(reqMap.get("product_id")))));
        afterService.setRental(rentalRepository.seeMyRentalDetail(Long.parseLong(String.valueOf(reqMap.get("rental_id")))));
        afterService.setUser(userRepository.seeUser(user_id));

        Long after_service_id = afterServiceRepository.save(afterService).getAfter_service_id();
        AfterService afterService1 = afterServiceRepository.seeAfterServiceDetail(after_service_id);
        if(files != null){
            int count_file = 0;
            for(MultipartFile file : files){
                String fileName = file.getName();
                Date date = new Date();
                SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
                String time = format1.format(date);
                fileName = "afterservice/"+user_id+"/"+after_service_id+"_"+time+"_"+fileName;
                String fileResult = fileUploadService.uploadImage(file,fileName);
                if(count_file == 0){
                    afterService1.setAfter_service_image(fileResult);
                }else{
                    afterService1.setAfter_service_image_second(fileResult);
                }
                count_file += 1;
            }
        }

        afterServiceRepository.save(afterService1);

        jsonObject.put("result",1);
        return jsonObject;
    }

    //AS 목록 보기
    @Override
    public JSONObject seeAfterService(Long user_id)throws Exception{

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("result",1);
        jsonObject.put("afterServices",afterServiceRepository.seeAfterService(user_id));

        return jsonObject;
    }

    //AS 상세보기
    @Override
    public JSONObject seeAfterServiceDetail(Long after_service_id)throws Exception{

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("result",1);
        jsonObject.put("afterService",afterServiceRepository.seeAfterServiceDetail(after_service_id));

        return jsonObject;
    }

    @Override
    public JSONObject cancelAs(Long after_service_id)throws Exception{
        JSONObject jsonObject = new JSONObject();

        AfterService afterService = afterServiceRepository.seeAfterServiceDetail(after_service_id);
        afterService.setAfter_service_state(0);
        afterServiceRepository.save(afterService);

        jsonObject.put("result",1);

        return jsonObject;
    }


}
