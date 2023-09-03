package com.example.ads.utils;

import com.example.ads.server.JavaClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PythonScriptInvokeUtil {
    public static Object invoke(String fileDir){
        JavaClient client = new JavaClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("file", fileDir);
        Map resultMap = new HashMap<>();
        try {
            String requestData = objectMapper.writeValueAsString(dataMap);
            System.out.println(requestData);
            StringBuilder responseData = client.accessClassify(requestData);
            resultMap = objectMapper.readValue(responseData.toString(), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultMap.get("result");
    }



    public static void main(String[] args) throws IOException {
        String dir = "C:/Users/perlicue/Desktop/master_lecture/aggregate_engineering/origin_nii";
        System.out.println("return :" + invoke(dir));
    }
}
