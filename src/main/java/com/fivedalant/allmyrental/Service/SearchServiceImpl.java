package com.fivedalant.allmyrental.Service;

import com.fivedalant.allmyrental.Jpa.Category;
import com.fivedalant.allmyrental.Jpa.Product;
import com.fivedalant.allmyrental.Jpa.Search;
import com.fivedalant.allmyrental.Repository.BrandRepository;
import com.fivedalant.allmyrental.Repository.CategoryRepository;
import com.fivedalant.allmyrental.Repository.ProductRepository;
import com.fivedalant.allmyrental.Repository.SearchRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("searchService")
public class SearchServiceImpl implements SearchService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    SearchRepository searchRepository;

    @Override
    public JSONObject seeMainProduct() throws Exception{
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("products",productRepository.product());
        jsonObject.put("categories",categoryRepository.findAll());

        return jsonObject;
    }

    //제품 검색 결과
    @Override
    public JSONObject seeSearchProduct(String searchWord, String order_category, int offset,Long category_id,Long brand_id) throws Exception{

        JSONObject jsonObject = new JSONObject();
        Search search = searchRepository.seeExistence(searchWord);  //검색어 존재여부에 따라 추천 검색어에 등록
        Date today = new Date();                                    //오늘 날짜

        jsonObject.put("advertisementProducts",productRepository.seeAdvertisementProduct(searchWord));
        if(category_id == 0 && brand_id == 0){
            jsonObject.put("searchProducts",productRepository.seeSearchProduct(searchWord,order_category,offset));
        }else if(category_id > 0 && brand_id == 0){
            jsonObject.put("searchProducts",productRepository.seeRecommendSearchCategory(searchWord,order_category,offset,category_id));
        }else if(brand_id > 0 && category_id == 0) {
            jsonObject.put("searchProducts",productRepository.seeRecommendSearchBrand(searchWord,order_category,offset,brand_id));
        }else{
            jsonObject.put("searchProducts",productRepository.seeRecommendSearchBoth(searchWord,order_category,offset,category_id,brand_id));
        }
        jsonObject.put("categories",categoryRepository.searchCategory(searchWord));
        jsonObject.put("brands",brandRepository.searchBrand(searchWord));

        if(search != null){
            search.setSearch_hits(search.getSearch_hits() + 1);
            search.setSearch_update(today);
            searchRepository.save(search);
        }else{
            Search insertSearch = new Search();
            insertSearch.setSearch_name(searchWord);
            insertSearch.setSearch_hits(1L);
            insertSearch.setSearch_regdate(today);
            insertSearch.setSearch_update(today);
            searchRepository.save(insertSearch);
        }

        return jsonObject;
    }

    //한번 더 검색
    @Override
    public JSONObject seeMoreProduct(String searchWord,String order_category,int offset) throws Exception{

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("searchProducts",productRepository.seeSearchProduct(searchWord,order_category,offset));

        return jsonObject;
    }

    //제품 상세보기
    @Override
    public JSONObject seeProductDetail(Long product_id)throws Exception{

        JSONObject jsonObject = new JSONObject();

        Product product = productRepository.seeProductDetail(product_id);

        jsonObject.put("product",product);
        product.setProduct_hits(product.getProduct_hits() + 1);
        productRepository.save(product);

        return jsonObject;
    }
}
