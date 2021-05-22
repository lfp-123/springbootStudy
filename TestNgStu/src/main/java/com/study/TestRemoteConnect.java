package com.study;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import ch.ethz.ssh2.*;
import org.apache.commons.net.ftp.FTPClient;
import sun.misc.IOUtils;

public class TestRemoteConnect {
    public static void main(String[] args) throws IOException {
//        String path = "/home/boss/wrong.txt";
//        String hostName = "10.1.8.69";
//        int port = 22;
//        String username = "boss";
//        String password = "ent#a17xV";
//        Connection ss = getConnect(hostName, username, password, port);
//      if (fileExist(path, ss)) {
//            readLogFile(path, ss);
//        }else {
//          System.out.println("文件不存在");
//      }
   //     getFileData("D:\\文档文件\\TestFile\\testNumber.txt");
        deleteFile("D:\\文档文件\\TestFile\\wrong.txt");
    }

    public static Connection getConnect(String hostName, String username, String password, int port) {
        String path = "/home/boss/wrong.txt";
        Connection conn = new Connection(hostName, port);
        try {
            // 连接到主机
            conn.connect();
            // 使用用户名和密码校验
            boolean isconn = conn.authenticateWithPassword(username, password);
            if (!isconn) {
                System.out.println("用户名称或者是密码不正确");
            } else {
                System.out.println("服务器连接成功.");
                //直接下载到本地
                SCPClient scpClient = conn.createSCPClient();
                scpClient.get(path,"D:\\文档文件\\TestFile");
                return conn;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean fileExist(String path, Connection conn) {
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
                        System.out.println("userId null");
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

    public static void readLogFile(String path, Connection conn) {
    String localPath = "D:\\文档文件\\TestFile\\testNumber.txt";
        if (conn != null) {
            Session ss = null;
            try {
                ss = conn.openSession();
                ss.execCommand("tail -100 ".concat(path));
                InputStream is = new StreamGobbler(ss.getStdout());
                BufferedReader brs = new BufferedReader(new InputStreamReader(is));
                FileWriter fw = new FileWriter(localPath);
                char[] buffer = new char[1024];
                int len= 0 ;
                while ((len = brs.read(buffer)) != -1) {
                    fw.write(buffer, 0, len);
                }
                fw.close();
                brs.close();
                fw.close();
//                while (true) {
//                    String line = brs.readLine();
//                    if (line == null) {
//                        break;
//                    } else {
//                        System.out.println(line);
//                    }
//                }
//                brs.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 连接的Session和Connection对象都需要关闭
                if (ss != null) {
                    ss.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }
    public static List<String> getFileData(String filePath) {
        ArrayList<String> list = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        String tempString = "";
        try {
            fr = new FileReader(filePath);
            //使用缓冲字符流进行包装
            br = new BufferedReader(fr);
            while ((tempString = br.readLine()) != null) {
                System.out.println(tempString);
                list.add(tempString);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static void deleteFile(String path){
        File file = new File(path);
        file.delete();
    }


}