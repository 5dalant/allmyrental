package com.fivedalant.allmyrental.Controller;

import com.fivedalant.allmyrental.Jpa.RecentSearch;
import com.fivedalant.allmyrental.Repository.ProductRepository;
import com.fivedalant.allmyrental.Repository.RecentSearchRepository;
import com.fivedalant.allmyrental.Repository.SearchRepository;
import com.fivedalant.allmyrental.Service.ProductService;
import com.fivedalant.allmyrental.Service.SearchService;
import com.fivedalant.allmyrental.Service.UserService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    SearchRepository searchRepository;

    @Autowired
    SearchService searchService;

    //검색 메인화면
    @GetMapping(value = "/api/search/main")
    @ResponseBody
    public JSONObject searchMain(){

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = searchService.seeMainProduct();
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("products",null);
        }
        return jsonObject;
    }

    //검색어 추천 목록
    @GetMapping(value = "/api/search/{searchWord}/seeRecommendSearch")
    @ResponseBody
    public JSONObject seeRecommendSearch(@PathVariable String searchWord){

        JSONObject jsonObject = new JSONObject();

        try {
            logger.info(searchWord);
            jsonObject.put("recommnedSearchs",searchRepository.seeRecommendSearch(searchWord));
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("recommnedSearchs",null);
        }
        return jsonObject;
    }

    //검색 결과
    @GetMapping(value = "/api/search/seeSearchProduct")
    @ResponseBody
    public JSONObject seeSearchProduct(@RequestParam("searchWord")String searchWord,@RequestParam("order_category")String order_category,@RequestParam("offset")int offset,@RequestParam("category_id")Long category_id,@RequestParam("brand_id")Long brand_id){

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject = searchService.seeSearchProduct(searchWord,order_category,offset,category_id,brand_id);
            jsonObject.put("result",1);
        }catch(Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result",2);
        }

        return jsonObject;
    }

    //검색 제품 더보기
    @GetMapping(value = "/api/search/seeMoreProduct")
    @ResponseBody
    public JSONObject seeMoreProduct(@RequestParam("searchWord")String searchWord,@RequestParam("order_category")String order_category,@RequestParam("offset")int offset){

        JSONObject jsonObject = new JSONObject();

        try {

        }catch (Exception e){

        }

        return jsonObject;
    }

    //검색 상새보기
    @GetMapping(value = "/api/search/{product_id}/seeProductDetail")
    @ResponseBody
    public JSONObject seeProductDetail(@PathVariable("product_id") Long product_id){

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = searchService.seeProductDetail(product_id);
        }catch (Exception e){
            logger.info(String.valueOf(e));
            jsonObject.put("result",3);
        }

        return jsonObject;
    }

//    @GetMapping(value = "/api/search/{user_id}/seeRecentSearch")
//    @ResponseBody
//    public JSONObject seeRecentSearch(ServletRequest request,@PathVariable("user_id") Long user_id){
//
//        JSONObject jsonObject = new JSONObject();
//
//        try {
//            if(userService.checkAuth((HttpServletRequest) request) && user_id != null){
//                jsonObject.put("result",1);
//                jsonObject.put("recentSearches",recentSearchRepository.seeRecentSearch(user_id));
//            }else{
//                jsonObject.put("result",2);
//                jsonObject.put("null",recentSearchRepository.seeRecentSearch(user_id));
//            }
//        }catch (Exception e){
//            jsonObject.put("result",3);
//        }
//
//        return jsonObject;
//    }
//
//    @PostMapping(value = "/api/search/{user_id}/deleteRecentSearch")
//    @ResponseBody
//    public JSONObject deleteRecentSearch(ServletRequest request,@PathVariable("user_id") Long user_id){
//
//        JSONObject jsonObject = new JSONObject();
//
//        return jsonObject;
//    }
}
