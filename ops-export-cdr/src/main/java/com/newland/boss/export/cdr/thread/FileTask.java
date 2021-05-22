package com.newland.boss.export.cdr.thread;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import com.newland.boss.export.cdr.bean.QueryCdrOperation;
import com.newland.boss.export.cdr.bean.StatusConstant;
import com.newland.boss.export.cdr.service.serviceImpl.CdrOperationImpl;
import com.nl.sri.sam.service.ISaInfoQueryer;
import com.nl.sri.sam.service.impl.SaInfoQueryer;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @author ${linfengpeng}
 * @Title: FileTask
 * @ProjectName ops-export-cdr
 * @Description: 文件线程
 * @date 2020/8/11 20:20
 */
public class FileTask {
    private org.apache.commons.logging.Log logger = LogFactory.getLog(FileTask.class);

    private String filePath;
    private String fileName;
    private QueryCdrOperation cdrOperation;
    private CdrOperationImpl impl;

    public FileTask(){

    }
    public FileTask(QueryCdrOperation cdrOperation,CdrOperationImpl impl) {
        this.filePath = cdrOperation.getBatchFilePath();
        this.fileName = cdrOperation.getBatchFileName();
        this.cdrOperation = cdrOperation;
        this.impl = impl;



    }
    public  Connection getConnect() {
        String sainfoPath = System.getenv("BOSS_ENCRYPT_ROOT")+"/ESainfo.txt";//System.getProperty("user.dir") + "/config/ESainfo.txt";
        logger.info("BOSS_ENCRYPT_ROOT:"+sainfoPath);
        ISaInfoQueryer cSaInfoQueryer = new SaInfoQueryer();
        cSaInfoQueryer.loadLibrary("nothing to load"); // XXXX已标注为@Deprecated 保留此接口为兼容旧版，无实际作用
        StringBuffer szUserName = new StringBuffer();
        StringBuffer szPasswd = new StringBuffer();//"prod.db.billing.bill"
        cSaInfoQueryer.openAndQueryByName(sainfoPath, cdrOperation.getBatchHost(), szUserName, szPasswd);
        String userName=szUserName.toString().split("@")[0];
        String url=szUserName.toString().substring(userName.length()+1);
        Connection conn = new Connection(url);
        Session session = null;
        try {
            // 连接到主机
            conn.connect();
            // 使用用户名和密码校验
            boolean isconn = conn.authenticateWithPassword(userName, szPasswd.toString());
            if (!isconn) {
                logger.info("用户名称或者是密码不正确");
            } else {
                logger.info("服务器连接成功.");
                session = conn.openSession();
                logger.info(filePath+fileName);
                String filePathFix = StatusConstant.UPLOAD_PATH;
                logger.info("路径："+filePathFix+filePath+fileName);
                session.execCommand("tail -100 ".concat(filePathFix+filePath+fileName));
                InputStream is = new StreamGobbler(session.getStdout());
                BufferedReader brs = new BufferedReader(new InputStreamReader(is));
                while (true) {
                    String line = brs.readLine(); //读取userId;
                    if (line == null) {
                        break;
                    } else {
                        String userId = line.trim();
                        logger.info("userId:"+userId);
                     //   ExportTaskPool.addExportTask(new ExportTaskThread(cdrOperation,impl,Long.parseLong(userId)));
                    }
                }
                brs.close();
            }
        } catch (Exception e) {
           logger.error("处理失败",e);
        }finally {
            // 连接的Session和Connection对象都需要关闭
            if (session != null) {
                session.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public  boolean fileExist(String path, Connection conn) {
        if (conn != null) {
            Session ss = null;
            try {
                ss = conn.openSession();
                ss.execCommand("ls -l ".concat(path));
                InputStream is = new StreamGobbler(ss.getStdout());
                BufferedReader brs = new BufferedReader(new InputStreamReader(is));
                String line = "";
                while (true) {
                    String lineInfo = brs.readLine();
                    if (lineInfo != null) {
                        line = line + lineInfo;
                    } else {
                        break;
                    }
                }
                brs.close();
                if (line != null && line.length() > 0 && line.startsWith("-")) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 连接的Session和Connection对象都需要关闭
                if (ss != null) {
                    ss.close();
                }
            }
        }
        return false;
    }
    public  void readLogFile(String path, Connection conn) {
        if (conn != null) {
            Session session = null;
            try {
                session= conn.openSession();
                session.execCommand("tail -100 ".concat(path));
                InputStream is = new StreamGobbler(session.getStdout());
                BufferedReader brs = new BufferedReader(new InputStreamReader(is));
                while (true) {
                    String line = brs.readLine();
                    if (line == null) {
                        break;
                    } else {
                        System.out.println(line);
                    }
                }
                brs.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 连接的Session和Connection对象都需要关闭
                if (session != null) {
                    session.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }
}
