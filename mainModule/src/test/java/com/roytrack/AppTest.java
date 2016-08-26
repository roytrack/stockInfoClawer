package com.roytrack;

import com.roytrack.service.ClawerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class AppTest {

    @Autowired
    ClawerService clawerService;

    @Test
    public void getJsonDataTest() throws IOException {
        clawerService.getJsonData(0);

    }


    @Test
    public void JsonToObject() throws IOException {
        clawerService.insertData("sddd");
    }



}
