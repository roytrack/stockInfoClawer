package com.roytrack.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roytrack.mapper.QuotationMapper;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 *
 *
 * Created by roytrack on 2016-08-26.
 */
@Service
public class ClawerService {

    private String urlPrefix="http://money.finance.sina.com.cn/d/api/openapi_proxy.php/?__s=[[%22hq%22,%22hs_a%22,%22%22,0,";
    private String urlPostfix=",80]]";

    HttpClientBuilder builder=HttpClientBuilder.create();
    CloseableHttpClient httpClient=builder.build();
    HttpContext localContext=new BasicHttpContext();

    @Autowired
    QuotationMapper quotationMapper;

    public void  insertData(String jsonData) throws IOException {
        File f=new File("D:\\wp\\stockInfoClawer\\doc\\data.sample.json");
        jsonData=FileUtils.readFileToString(f,"UTF-8");
        JSONArray va=JSON.parseArray(jsonData);
        System.out.println();
        JSONObject ob=(JSONObject)va.get(0);
        JSONArray items=(JSONArray)ob.get("items");
        System.out.println(items.size());










    }

    /**
     * http://money.finance.sina.com.cn/d/api/openapi_proxy.php/?__s=[[%22hq%22,%22hs_a%22,%22%22,0,36,80]]
      * @param page
     * @throws IOException
     */
    public  String  getJsonData(int page) throws IOException {
        System.out.println("url is :"+urlPrefix+page+urlPostfix);
        HttpGet httpGet=new HttpGet(urlPrefix+page+urlPostfix);
        HttpResponse response=httpClient.execute(httpGet,localContext);
        HttpHost target=(HttpHost)localContext.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);
        System.out.println("Final target :"+target);
        HttpEntity entity=response.getEntity();
        if(entity!=null){
            String data=EntityUtils.toString(entity);
            System.out.println(data);
            return data;
        }
        return "";
    }
}
