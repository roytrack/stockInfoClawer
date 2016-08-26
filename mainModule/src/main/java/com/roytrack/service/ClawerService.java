package com.roytrack.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roytrack.mapper.QuotationMapper;
import com.roytrack.model.Quotation;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by roytrack on 2016-08-26.
 */
@Service
public class ClawerService {

    private String urlPrefix="http://money.finance.sina.com.cn/d/api/openapi_proxy.php/?__s=[[%22hq%22,%22hs_a%22,%22%22,0,";
    private String urlPostfix=",80]]";
    private String date=" ";

    HttpClientBuilder builder=HttpClientBuilder.create();
    CloseableHttpClient httpClient=builder.build();
    HttpContext localContext=new BasicHttpContext();
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Autowired
    QuotationMapper quotationMapper;


    public void all() throws IOException, ParseException {
        for(int i=0;i<36;i++){
            i=36;
            String json=getJsonData(i);
            insertData(json);
        }
    }

    public void  insertData(String jsonData) throws IOException, ParseException {
//        File f=new File("D:\\wp\\stockInfoClawer\\doc\\data.sample.json");
//        jsonData=FileUtils.readFileToString(f,"UTF-8");
        JSONArray va=JSON.parseArray(jsonData);
        System.out.println();
        JSONObject ob=(JSONObject)va.get(0);
        date=ob.getString("day")+date;
        JSONArray items=(JSONArray)ob.get("items");
        System.out.println(items.size());
        if(items.size()<=0){
            System.out.println("no data");
            return;
        };
        List<Quotation> list=new ArrayList<>();
        for(Object o:items){
            JSONArray a=(JSONArray)o;
            Quotation q=new Quotation();
            q.setTransDate(sdf.parse(date+"00:00:00"));
            for(int i=0;i<24;i++){
                Object v=a.get(i);
                String vs=null;
                BigDecimal vb=null;
                Integer vi=null;
                if(v instanceof String){
                    vs=(String)v;
                }else if(v instanceof BigDecimal){
                    vb=(BigDecimal)v;
                }else if(v instanceof Integer){
                    vi=(Integer)v;
                }
                switch (i){
                    case 0:q.setSymbol(vs);break;
                    case 1:q.setCode(vs);break;
                    case 2:q.setName(vs);break;
                    case 3:q.setTrade(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs)) ;break;
                    case 4:q.setPricechange(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 5:q.setChangepercent(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 6:q.setBuy(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 7:q.setSell(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 8:q.setSettlement(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 9:q.setOpen(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 10:q.setHigh(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 11:q.setLow(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 12:q.setVolume(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 13:q.setAmount(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 14:q.setTicktime(sdf.parse(date+vs));break;
                    case 15:q.setPer(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 16:q.setPerD(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 17:q.setNta(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 18:q.setPb(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 19:q.setMktcap(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 20:q.setNmc(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 21:q.setTurnoverratio(vs==null?(vb==null?new BigDecimal(vi):vb):new BigDecimal(vs));break;
                    case 22:q.setFavor(vs);break;
                    case 23:q.setGuba(vs);break;
                }

            }
            list.add(q);
        }
        System.out.println("get val"+list.size());
        for(Quotation q:list){
            quotationMapper.insert(q);
        }
        System.out.println("insert finish");
        date=" ";
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
