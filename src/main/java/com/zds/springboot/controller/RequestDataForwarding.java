package com.zds.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.zds.springboot.common.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/request_data_forwarding")
public class RequestDataForwarding {
    @RequestMapping("/stock_inquiry")
    public Object stockInquiry(String matName){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.1.128.59:7002/ncpmsmm/receiveMessage";
        Class responseType = String.class;
        HttpHeaders head = new HttpHeaders();
        head.add("Content-Type","application/json");
        head.add("serviceId","MM_QLT_01");
        String body = "";
        if (!matName.equals("") && !(matName == null)){
            body = "{\n" +
                    "    \"matName\":\""+matName+"\"\n" +
                    "}\n";
        }
        HttpEntity<String> entity = new HttpEntity(body, head);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, responseType);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        return jsonObject;
    }
    @RequestMapping("/get_goods_all_life")
    public Result getGoodsAllLife(String batchNo){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.1.128.59:7002/ncpmsmm/receiveMessage";
        Class responseType = String.class;
        HttpHeaders head = new HttpHeaders();
        head.add("Content-Type","application/json");
        head.add("serviceId","GET_GOODS_ALL_LIFE");
        HttpEntity<String> entity = new HttpEntity("{\n" +
                "    \"batchNo\":\""+batchNo+"\"\n" +
                "}",head);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, responseType);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        return Result.success(jsonObject);
    }

    @RequestMapping("/get_goods")
    public Result getGoods(String warehouseNo, String matCode){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.1.1.211:7002/ncpmsmm/receiveMessage";
        Class responseType = String.class;
        HttpHeaders head = new HttpHeaders();
        head.add("Content-Type","application/json");
        head.add("serviceId","GET_GOODS");
        HttpEntity<String> entity = new HttpEntity("{\"warehouseNo\":\""+ warehouseNo +"\",\"matCode\":\""+ matCode +"\"}",head);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, responseType);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        return Result.success(jsonObject);
    }

    @RequestMapping("get_expend_goods")
    public Result getExpendGoods(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.1.128.59:7002/ncpmsmm/receiveMessage";
        Class responseType = String.class;
        HttpHeaders head = new HttpHeaders();
        head.add("Content-Type","application/json");
        head.add("serviceId","GET_EXPEND_GOODS");
        HttpEntity<String> entity = new HttpEntity("{\"warehouseNo\":\"02B030503\",\"matCode\":\"60000000035\"}",head);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, responseType);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        return Result.success(jsonObject);
    }


}
