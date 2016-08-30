package com.roytrack;

import com.roytrack.service.ClawerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class AppTest {

    @Autowired
    ClawerService clawerService;

//    @Test
//    public void getJsonDataTest() throws IOException {
//        clawerService.getJsonData(0);
//
//    }
//
//
//    @Test
//    public void JsonToObject() throws IOException, ParseException {
//        clawerService.insertData("sddd");
//    }
//
//    @Test
//    public void parseDate() throws ParseException {
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(new Date()));
//        String a="2016-08-25 16:30:03";
//        Date d=sdf.parse(a);
//        System.out.println(d.getTime());
//
//    }


    @Test
    public void all() throws IOException, ParseException {
        clawerService.all();
    }


}
