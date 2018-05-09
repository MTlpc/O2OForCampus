package com.example.test.dao;

import com.example.o2o.dao.HeadLineDao;
import com.example.o2o.entity.HeadLine;
import com.example.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeadLineTest extends BaseTest {

    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testQueryHeadLine() {
        List<HeadLine> handLineList = headLineDao.queryHeadLine(new HeadLine());
        assertEquals(0, handLineList.size());
    }

}
