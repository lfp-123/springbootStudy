package com.newland.boss.export.cdr.util;

import com.nl.sri.sam.service.ISaInfoQueryer;
import com.nl.sri.sam.service.impl.SaInfoQueryer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author ${linfengpeng}
 * @Title: DButil
 * @ProjectName ops-export-cdr
 * @Description: Session获取类
 * @date 2020/8/10 9:42
 */
public class DButil {
    private static Log logger = LogFactory.getLog(DButil.class);
    private static InputStream inputStream = null;
    private static SqlSessionFactory sqlSessionFactory = null;
    private  SqlSession session = null;
    public static Connection conn = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;
    private static String url;//请求地址
    private static String username;//数据库账号
    private static String password;//数据库密码
    private static String driver;
    private static String sainfoPath;

    static {
        Properties pro=new Properties();
        try {
            //jdbc
            sainfoPath = ConfRead.getConf("PwdFile");
            InputStream is=DButil.class.getClassLoader()
                    .getResourceAsStream("jdbc.properties");
            pro.load(is);
            String pname = pro.getProperty("jdbc.pname");
            logger.info("pname:"+pname);
            if("prod.db.logdb_pri.billlog.j".equals(pname)){
                pro.setProperty("jdbc.username",MybaisUtil.decryptDecode(pro.getProperty("jdbc.username")));
                pro.setProperty("jdbc.password",MybaisUtil.decryptDecode(pro.getProperty("jdbc.password")));
                pro.setProperty("jdbc.url",MybaisUtil.decryptDecode(pro.getProperty("jdbc.url")));
                pro.setProperty("jdbc.driver",MybaisUtil.decryptDecode(pro.getProperty("jdbc.driver")));
                String[] strings = usingSam(pname);
                username = strings[0];
                url = pro.getProperty("jdbc.url");
                password = strings[2];
            }else {
                username = pro.getProperty("jdbc.username");
                password = pro.getProperty("jdbc.password");
                url = pro.getProperty("jdbc.url");
            }
            logger.info("uname:"+username+":"+password+":"+url);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //mybatis
            inputStream = Resources.getResourceAsStream("mybatis.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,pro);
    }

    /**
     * 单例模式
     * @return
     */
    public  SqlSession getSession(){
        //mybatis session
        session = sqlSessionFactory.openSession();
        return session;
    }


    //获取连接方法
    public  Connection getConnection(){

        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }


    public static String[] usingSam(String key) {
        String[] arrays = new String[3];
        StringBuffer szUserName = new StringBuffer();
        StringBuffer szPasswd = new StringBuffer();
        //密码库解析函数
        ISaInfoQueryer cSaInfoQueryer = new SaInfoQueryer();
        cSaInfoQueryer.loadLibrary("nothing to load");
        cSaInfoQueryer.openAndQueryByName(sainfoPath, key, szUserName, szPasswd);
        String temp = szUserName.toString();
        arrays[0] = temp.substring(0, temp.indexOf('@'));
        arrays[1] = temp.substring(temp.indexOf('@') + 1);
        arrays[2] = szPasswd.toString();
        return arrays;
    }
}
