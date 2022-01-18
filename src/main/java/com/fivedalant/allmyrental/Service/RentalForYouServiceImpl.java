package com.fivedalant.allmyrental.Service;

import com.fivedalant.allmyrental.Jpa.Product;
import com.fivedalant.allmyrental.Jpa.User;
import com.fivedalant.allmyrental.Repository.CategoryRepository;
import com.fivedalant.allmyrental.Repository.ProductRepository;
import com.fivedalant.allmyrental.Repository.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("rentalForYouService")
public class RentalForYouServiceImpl implements RentalForYouService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    //추천 제품 목록
    @Override
    public JSONObject seeRecommendProduct(Long user_id)throws Exception{

        JSONObject jsonObject = new JSONObject();

        User user = userRepository.seeUser(user_id);

        if(user != null){
            String[] favoriteList  = user.getFavorite().split("\\|"); //관심사 | 기준으로 나누기
            ArrayList<List<Product>> resultList = new ArrayList<List<Product>>();
            for (String s : favoriteList) {
                Long categoryId = categoryRepository.seeCategory(s).getCategory_id();
                resultList.add(productRepository.seeRecommendProduct(categoryId)); //관심사 별로 3개씩 리스트 가져오기
            }
            jsonObject.put("result",1);
            jsonObject.put("products",resultList);
        }else{
            jsonObject.put("result",3);
        }

        return jsonObject;
    }
}
