package org.concurrent.fish;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author ${林锋鹏}
 * @Title: Test5
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/3/10 16:39
 */
    class Test5 {
        // 1. 连接池大小
        private int poolSize;
        // 2. 连接对象数组
        private Connection[] connections;
        // 3. 连接状态数组 0 表示空闲， 1 表示繁忙
        private AtomicIntegerArray states;
        // 4. 构造方法初始化
        public Test5(int poolSize) {
            this.poolSize = poolSize;
            this.connections = new Connection[poolSize];
            this.states = new AtomicIntegerArray(new int[poolSize]);
            for (int i = 0; i < poolSize; i++) {
               // connections[i] = new MockConnection("连接" + (i+1));
            }
        }

    // 5. 借连接
        public Connection borrow() {
            while(true) {
                for (int i = 0; i < poolSize; i++) {
// 获取空闲连接
                    if(states.get(i) == 0) {
                        if (states.compareAndSet(i, 0, 1)) {
//                            log.debug("borrow {}", connections[i]);
                            return connections[i];
                        }
                    }
                }
// 如果没有空闲连接，当前线程进入等待
                synchronized (this) {
                    try {
//                        log.debug("wait...");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // 6. 归还连接
        public void free(Connection conn) {
            for (int i = 0; i < poolSize; i++) {
                if (connections[i] == conn) {
                    states.set(i, 0);
                    synchronized (this) {
//                        log.debug("free {}", conn);
                        this.notifyAll();
                    }
                    break;
                }
            }
        }
    }
//    class MockConnection implements Connection {
//// 实现略
//    }

