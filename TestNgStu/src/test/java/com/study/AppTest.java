package com.study;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */


    public class TestNGLearn1 {
        @BeforeClass
        public void beforeClass() {
            System.out.println("this is before class");
        }

        @Test
        public void TestNgLearn() {
            System.out.println("this is TestNG test case");
        }

        @AfterClass
        public void afterClass() {
            System.out.println("this is after class");
        }


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

}
