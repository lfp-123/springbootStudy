package com.study;

        import org.testng.Assert;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;

/**
 * @author ${林锋鹏}
 * @Title: CalculateTest
 * @ProjectName TestNgStu
 * @Description: TODO
 * @date 2020/5/19 14:02
 */
public class CalculateTest {
    @Test(enabled = false)//false 忽略测试此测试用例
    public void testAdd() {
        /**
         * assertEquals这个方法是一个断言方法
         * 第一个参数表示程序的执行结果
         * 第二个参数表示预期的结果
         * 当预期结果与执行结果是一致的时候，则表示单元测试成功
         */
        Assert.assertEquals( new Calculate().add(1, 3),4);
        System.out.println("testAdd()");
    }


    @Test
    public void testSubtract() {
        Assert.assertEquals(new Calculate().subtract(9, 5),4 );
        System.out.println("testSubtract()");
    }

    @Test
    public void testMultiply() {
        System.out.println("testMultiply()");
        Assert.assertEquals(new Calculate().multiply(2, 3),6 );

    }

    /**
     * 预期异常测试
     */
    @Test(expectedExceptions =ArithmeticException.class)
    public void testDivide() {
        Assert.assertEquals( new Calculate().divide(9, 0),3);
        System.out.println("testDivide()");
    }
/**
 * 参数化测试
 */
    @DataProvider(name = "user")
    public Object[][] user(){
        return new Object[][] {
                {"root","password"},
                {"newland.com","tianxia"},
                {"tank","ming"}
        };
    }
    @Test(dataProvider = "user")
    public  void  verfiyser(String username,String password){
        System.out.println(username+" "+password);
    }
    /**
     * 依赖测试
      */
    @Test
    public void setA(){
        System.out.println("VIP");
    }
    @Test
    public void setAA(){
        System.out.println("VIP1");
    }
    @Test(dependsOnMethods = {"setA","setAA"})
    public void setB(){
        System.out.println("pool");
    }
}
