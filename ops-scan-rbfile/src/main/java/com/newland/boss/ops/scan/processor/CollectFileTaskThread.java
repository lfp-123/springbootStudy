package com.newland.boss.ops.scan.processor;

import com.newland.boss.bbf.middleware.mq.activemq.ActiveMqTemplate;
import com.newland.boss.bbf.third.sftp.SftpTemplate;
import com.newland.boss.ops.scan.bean.Constant;
import com.newland.boss.ops.scan.bean.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author lfp
 * @Title: CollectFileTaskThread
 * @ProjectName ops
 * @Description: 文件处理线程
 * @date 2021/1/25 9:09
 */

public class CollectFileTaskThread extends Thread {
    private Logger logger = LoggerFactory.getLogger(CollectFileTaskThread.class);
    private ResourceConfig resourceConfig;
    private SftpTemplate sftpTemplate;
    private ActiveMqTemplate activeMqTemplate;
    private static Boolean RUNNING = true;

    public CollectFileTaskThread(ResourceConfig config, SftpTemplate sftpTemplate, ActiveMqTemplate activeMqTemplate){
        this.resourceConfig = config;
        this.sftpTemplate = sftpTemplate;
        this.activeMqTemplate = activeMqTemplate;
    }
    @Override
    public void run() {
        String localFileName = null;
        while (RUNNING){
                try{
                    //2、扫描路径下的文件，得到list
                    List<String> fileList = sftpTemplate.scan(resourceConfig.getRemotePath());
                    //3、遍历
                    for (String remoteFile : fileList) {
                        //4、下载到本地临时目录
                        localFileName = sftpTemplate.get(remoteFile, resourceConfig.getLocalPathTmp());
                        //5、做大小检查
                        long localSize = sftpTemplate.getLocalFileSize(localFileName);
                        long remoteSize = sftpTemplate.getRemoteFileSize(remoteFile);
                        if (localSize != remoteSize) {
                            localFileName = sftpTemplate.get(remoteFile, resourceConfig.getLocalPathTmp());
                        } else {
                            //6、远程文件的处理
                            int dealNumber = resourceConfig.getRemoteDealType();
                            if (dealNumber == Constant.DEAL_ACCORDING_DELETE) {
                                //删除
                                sftpTemplate.rm(remoteFile);
                            } else if (dealNumber == Constant.DEAL_ACCORDING_MOVE) {
                                //备份
                                sftpTemplate.moveRemoteFile(remoteFile, resourceConfig.getRemotePathBack());
                            }
                            //7、本地临时目录移动到存储目录
                            sftpTemplate.moveLocalFile(localFileName, resourceConfig.getLocalPath());
                            //8、推入mq
                            activeMqTemplate.send("",localFileName);
                            activeMqTemplate.commit();
                        }
                    }
                    Thread.sleep(5);
                }catch (Exception e){
                logger.info("处理异常"+e);
                    //消息事务回滚
                    try {
                        activeMqTemplate.rollback();
                    } catch (Exception el) {
                        logger.info("处理异常"+e);
                    }
                }

        }
    }
}
