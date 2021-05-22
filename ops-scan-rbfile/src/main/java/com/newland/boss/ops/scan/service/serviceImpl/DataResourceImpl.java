package com.newland.boss.ops.scan.service.serviceImpl;

import com.newland.boss.bbf.middleware.zk.lock.ZkDistributedLock;
import com.newland.boss.bbf.middleware.zk.zk.ZookeeperTemplate;
import com.newland.boss.bbf.middleware.zk.zk.impl.ZookeeperTemplateImpl;
import com.newland.boss.ops.scan.bean.ResourceConfig;
import com.newland.boss.ops.scan.mapper.ResourcesDao;
import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @descriptions:
 * @author zhangfaquan
 * @version 1.0
 * @created 2020/7/11 20:11
 */
@Service
public class DataResourceImpl {
	private String hosts = "10.1.8.69:2181";
	private Integer sessionTimeoutMs = 7000;
	private Integer connectionTimeoutMs = 15000;
	private Integer retryCount = 2;
	private Integer elapsedTimeMs = 2000;
	private String lockNodePath = "/lock_path";
	private String dataNodePath = "/config_id";
	private String NODE_NAME = "config_";
	private ZookeeperTemplate zkt = null;
	private String configPath;
	private Integer collectorNum = 4;

    @Autowired
	private ResourcesDao resourcesDao;

    public DataResourceImpl() {
		zkt = new ZookeeperTemplateImpl();
	}

    /**
     * @descriptions 通过分布式锁的方式获取配置源记录
     * @return java.util.List<com.newland.boss.adc.collect.module.ResourceConfig>
     */
    public List<ResourceConfig> getResourceList() {
        List<ResourceConfig> configuration = null;
        try {
            zkt.connect(hosts, sessionTimeoutMs, connectionTimeoutMs, retryCount, elapsedTimeMs);
            ZkDistributedLock zkDistributedLock = new ZkDistributedLock(hosts);
            checkExistsAndCreateNode(lockNodePath);
            checkExistsAndCreateNode(dataNodePath);
            zkDistributedLock.lock(lockNodePath);

            // 判断当前是否还有未被分配的配置，如果没有就等待。（用于热备）
            boolean flag = true;
            while(flag){
                configuration = this.getConfiguration();
                if(configuration != null){
                    flag = false;
                }
            }
            zkDistributedLock.unlock();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }
	/**
	 * @descriptions 获取配置源记录
	 * @return java.util.List<com.newland.boss.adc.collect.module.ResourceConfig>
	 */
	private List<ResourceConfig> getConfiguration() {
		// 记录当前采集器分配到的配置记录
		List<ResourceConfig> con = null;
		try {
			// 记录config_id下的子节点名
			List<String> childrenNode = null;
			// 记录config_id下的子节点的数据域
			List<String> nodeData = new ArrayList<>();
			// 查询数据库总记录数
			Integer i = resourcesDao.queryCFGcount();
			// 查询各个采集器各自拥有的配置源的主键的集合
			childrenNode = zkt.getChildPath(dataNodePath);
			if(childrenNode != null && !childrenNode.isEmpty()){
				childrenNode.forEach(cn-> {
					try {
						String cnodeData = zkt.getNodeData(dataNodePath + "/" + cn);
						List<String> configId = Arrays.asList(cnodeData.split(","));
						nodeData.addAll(configId);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}

			// 查询所有的配置记录
			List<ResourceConfig> configurations = resourcesDao.queryCFGAll();
			if (configurations != null && !configurations.isEmpty()){
				// 过滤出未被分配的记录
				configurations = configurations.stream()
						.filter(configuration -> !nodeData.contains(configuration.getProcessId()+"-"+configuration.getTaskId()))
						.collect(Collectors.toList());
				System.out.println("未被分配的记录"+configurations);
				if (!configurations.isEmpty()){
					// 平均分配剩余配置文件
					Integer usedCollector = (childrenNode == null) ? 0 : childrenNode.size();
					//更改为平均分配返回最大值、
					List<Integer> avg = ZookeeperTemplate.avg(configurations.size(), collectorNum - usedCollector);
					// 记录当前采集器分配到的配置记录
					Integer index = 0;
					for (int idx = 0, len = avg.size(); idx < len; idx++) {
						if(avg.get(idx) != 0){
							index = idx;
							break;
						}
					}

					// 记录当前采集器分配到的配置记录数量
					con = new ArrayList<>();
					for (int j = 0; j < avg.get(index); j++) {
						con.add(configurations.get(j));
					}

					// 记录当前采集器的分配到的配置
					List<String> config_id = con.stream().map(configuration -> configuration.getProcessId()+"-"+configuration.getTaskId()).collect(Collectors.toList());
					String currentNodeDate = StringUtils.join(config_id, ",");
					try {
						this.configPath = zkt.createEphemeralSequentialNode(dataNodePath + "/" + NODE_NAME, currentNodeDate);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("当前采集器的分配到的配置"+config_id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * @descriptions 判断节点是否存在，不存在则创建
	 * @param nodePath 节点路径
	 * @return void
	 */
	private void checkExistsAndCreateNode(String nodePath) {
		try {
			Stat nodeStat = zkt.isExists(nodePath);
			if (nodeStat == null) zkt.createPersistentNode(nodePath, nodePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
