package com.fivedalant.allmyrental.Service;

import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface AfterServiceService {

    public JSONObject createAfterService(Long user_id, @RequestParam HashMap<String, Object> reqMap, List<MultipartFile> files)throws Exception;

    public JSONObject seeAfterService(Long user_id)throws Exception;

    public JSONObject seeAfterServiceDetail(Long after_service_id)throws Exception;

    public JSONObject cancelAs(Long after_service_id)throws Exception;

}
