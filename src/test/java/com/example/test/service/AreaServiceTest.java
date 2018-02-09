package com.example.test.service;

import com.example.o2o.entity.Area;
import com.example.o2o.service.AreaService;
import com.example.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void TestGetAreaList(){
        List<Area> areaList = areaService.getAreaList();

        assertEquals("东苑",areaList.get(0).getAreaName());
    }
}
