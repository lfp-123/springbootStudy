package com.newland.netty.study.springbootnettynio.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author lfp\
 * @Title: Test2
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/2/1 10:52
 */
public class CfgWriter {
    private PrintWriter out;
    private static final String DEFINE = "define";

    public CfgWriter(File file) throws FileNotFoundException {
        super();
        this.out = new PrintWriter(file);
    }

    public void writeComment(String comment) {
        out.print('#');
        out.println(comment);
    }

    public void startDefine(String name) {
        out.print(DEFINE);
        out.print(' ');
        out.print(name);
        out.println('{');
    }

    public void writeProperty(String key, String value) {
        out.print(' ');
        out.print(key);
        out.print('\t');
        out.println(value);
    }

    public void endDefine() {
        out.println('}');
    }

    public void close() {
        out.close();
    }

    public static void main(String[] args) throws Exception {
        CfgWriter w = new CfgWriter(new File("d:/config.cfg"));
        w.writeComment("config.cfg");
        w.startDefine("Server");
        w.writeProperty("Id", "0001");
        w.writeProperty("Name", "xxxx");
        w.writeProperty("Amount", "100");
        w.endDefine();
        w.close();
    }
}


