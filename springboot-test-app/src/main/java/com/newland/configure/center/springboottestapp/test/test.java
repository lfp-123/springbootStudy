package com.newland.configure.center.springboottestapp.test;

import java.io.File;
import java.io.IOException;

/**
 * @author ${林锋鹏}
 * @Title: test
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/3/4 15:26
 */
public class test {

    public static void main(String[] args) throws IOException {
        String path ="D:\\home\\streams\\billApp\\ops-imexport-cdr\\ops-export-cdr\\PMSTmp";
        File oldFile = new File(path);
        if (!oldFile.exists()) {
            oldFile.createNewFile();
        }
        System.out.println("修改前文件名称是：" + oldFile.getName());
        String rootPath = oldFile.getParent();
        System.out.println("根路径是：" + rootPath);
        File newFile = new File(rootPath + File.separator + "bbf.cfg");
        System.out.println("修改后文件名称是：" + newFile.getName());
        if (oldFile.renameTo(newFile)) {
            System.out.println("修改成功!");
        }
    }
}
