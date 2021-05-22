package com.newland.boss.export.cdr;




import com.newland.boss.export.cdr.bean.QueryCdrOperation;
import com.newland.boss.export.cdr.bean.StatusConstant;
import com.newland.boss.export.cdr.service.serviceImpl.CdrOperationImpl;
import com.newland.boss.export.cdr.thread.ExportTask;
import com.newland.boss.export.cdr.thread.ExportTaskPool;
import com.newland.boss.export.cdr.thread.FileTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author ${linfengpeng}
 * @Title: StartupInit
 * @ProjectName TempProject
 * @Description: TODO
 * @date 2020/8/10 10:06
 */
public class LoadCdr {
    private Log logger = LogFactory.getLog(LoadCdr.class);
    private static boolean RUNNING = true;
    private static CdrOperationImpl cdrImpl = new CdrOperationImpl();

    public static void main(String[] args) {
        new LoadCdr().LoadStart();
    }
    public void LoadStart() {
        logger.info("export procedure start ");
        while (RUNNING) {
            //查询服务 自循环  查询userID  ,StartTime ,endTime
            List<QueryCdrOperation> cdrList = cdrImpl.queryCdrOperationList();
            if(cdrList.size()==0){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            for (QueryCdrOperation cdrOperation : cdrList) {
                //线程池+单用户ID
                // 更新时间状态 1  查询hbase : 防止旁支线程未修改 主线程继续查询。
                cdrOperation.setStatus(StatusConstant.QUERY_STATUS);
                cdrImpl.insertCdrOperation(cdrOperation);
                if ("".equals(cdrOperation.getUserId())|| null == cdrOperation.getUserId()) {
                    // 文件 批量 取 userId 得到rowKey
                    FileTask fileTask = new FileTask(cdrOperation,cdrImpl);
                    fileTask.getConnect();
                }else {
                     ExportTaskPool.addExportTask(new ExportTask(StatusConstant.sqlMap,cdrOperation.getUserId(),String.valueOf(cdrOperation.getOperationId()),cdrOperation,cdrImpl));
                }
            }
        }
    }
}
