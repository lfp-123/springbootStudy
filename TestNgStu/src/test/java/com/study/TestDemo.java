package com.study;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author ${林锋鹏}
 * @Title: TestDemo
 * @ProjectName TestNgStu
 * @Description: TODO
 * @date 2020/5/19 13:46
 */
public class TestDemo {
    @Test
    public void testcase1(){
        Assert.assertTrue(false);
        System.out.println("testcase1");
    }
    @Test
    public void testcase2(){
        Assert.assertTrue(true);
        System.out.println("testcase1");
    }
}
