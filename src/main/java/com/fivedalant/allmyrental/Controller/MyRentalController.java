package com.fivedalant.allmyrental.Controller;

import com.fivedalant.allmyrental.Jpa.Product;
import com.fivedalant.allmyrental.Repository.ProductRepository;
import com.fivedalant.allmyrental.Service.MyRentalService;
import com.fivedalant.allmyrental.Service.ProductService;
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
public class MyRentalController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    MyRentalService myRentalService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    //렌탈 목록 가져오기
    @GetMapping(value = "/api/myRental/{user_id}/seeMyRentals")
    @ResponseBody
    public JSONObject seeMyRentals(ServletRequest request, @PathVariable("user_id") Long user_id, @RequestParam("offset") int offset) {

        JSONObject jsonObject = new JSONObject();

        try {
            if (userService.checkAuth((HttpServletRequest) request, user_id)) {
                jsonObject = myRentalService.seeMyRentals(user_id, offset);
                jsonObject.put("result", 1);
            } else {
                jsonObject.put("result", 2);
            }
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

    //렌탈 제품 등록
    @PostMapping(value = "/myRental/{user_id}/createMyRental")
    @ResponseBody
    public JSONObject createMyRental(@PathVariable("user_id") Long user_id, @RequestParam HashMap<String, Object> reqMap, @RequestParam(value = "files", required = false) MultipartFile files) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = myRentalService.createMyRental(user_id, reqMap, files);
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

    //마이렌탈 상세보기
    @GetMapping(value = "/api/myRental/{user_id}/seeMyRentalDetail/{rental_id}")
    @ResponseBody
    public JSONObject seeMyRentalDetail(ServletRequest request, @PathVariable("user_id") Long user_id, @PathVariable("rental_id") Long rental_id) {

        JSONObject jsonObject = new JSONObject();

        try {
            if (userService.checkAuth((HttpServletRequest) request, user_id)) {
                jsonObject = myRentalService.seeMyRentalDetail(rental_id);
                jsonObject.put("result", 1);
            } else {
                jsonObject.put("result", 2);
            }
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

    //마이렌탈 정기관리 신청
    @PostMapping(value = "/api/myRental/{user_id}/createRegularManage")
    @ResponseBody
    public JSONObject createRegularManage(ServletRequest request, @PathVariable("user_id") Long user_id, @RequestParam HashMap<String, Object> reqMap) {

        JSONObject jsonObject = new JSONObject();

        try {
            if (userService.checkAuth((HttpServletRequest) request, user_id)) {
                jsonObject = myRentalService.createRegularManage(user_id, reqMap);
            } else {
                jsonObject.put("result", 2);
            }
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

    //제품 셀프 관리
    @GetMapping(value = "/myRental/seeSelfManage/{product_id}")
    @ResponseBody
    public JSONObject seeSelfManage(@PathVariable("product_id") Long product_id) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = myRentalService.seeSelfManage(product_id);
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

    //검색 상새보기
    @GetMapping(value = "/api/myRental/{product_id}/seeProductDetail")
    @ResponseBody
    public JSONObject seeProductDetail(@PathVariable("product_id") Long product_id) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = productService.seeProductDetail(product_id);
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

    @GetMapping(value = "/api/myRental/searchProducts")
    @ResponseBody
    public JSONObject searchProducts(@RequestParam("product_name") String product_name) {
        JSONObject jsonObject = new JSONObject();

        try {
            List<Product> products = productRepository.searchProductName(product_name);
            if (products == null) {
                jsonObject.put("result", 2);
            } else {
                jsonObject.put("result", 1);
                jsonObject.put("products", products);
            }
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

    @PostMapping(value = "/api/myRental/{rental_id}/deleteContractImage")
    @ResponseBody
    public JSONObject deleteContractImage(@PathVariable Long rental_id){
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject = myRentalService.deleteContractImage(rental_id);
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

    @PostMapping(value = "/api/{user_id}/myRental/{rental_id}/deleteContractImage")
    @ResponseBody
    public JSONObject uploadContractImage(@PathVariable Long rental_id,@RequestParam(value = "files", required = false) MultipartFile files,@PathVariable Long user_id)throws Exception{
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject =myRentalService.uploadContractImage(rental_id,files,user_id);
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result", 3);
        }

        return jsonObject;
    }

}
