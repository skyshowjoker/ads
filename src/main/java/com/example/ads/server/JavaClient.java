package com.example.ads.server;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class JavaClient {

    public StringBuilder accessClassify(String requestData){
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL("http://localhost:5000/classify");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 发送请求数据
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(requestData);
            writer.flush();
            writer.close();

            // 获取响应数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 解析并处理响应数据
//            int result = Integer.parseInt(response.toString().trim());
            System.out.println("Python返回的乘积结果：" + response);

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:5000/classify");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 构造请求数据
            String requestData = "{\"file\": \"C:/Users/perlicue/Desktop/master_lecture/aggregate_engineering/origin_nii\"}";

            // 发送请求数据
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(requestData);
            writer.flush();
            writer.close();

            // 获取响应数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 解析并处理响应数据
//            int result = Integer.parseInt(response.toString().trim());
            System.out.println("Python返回的乘积结果：" + response);

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
