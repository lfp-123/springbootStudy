package com.study;

import java.io.File;

/**
 * @author ${林锋鹏}
 * @Title: TestPowerMock
 * @ProjectName TestNgStu
 * @Description: TODO
 * @date 2020/5/20 8:39
 */
public class TestPowerMock {

    /**
     * 普通mock 测试用例代码
     */
    public boolean callArgumentInstance(File file){
        return file.exists();
    }

}
