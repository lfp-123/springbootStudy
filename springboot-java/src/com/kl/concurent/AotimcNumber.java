package com.kl.concurent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ${林锋鹏}
 * @Title: AotimcNumber
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/4/22 22:02
 */
public class AotimcNumber {
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();
        int i = atomicInteger.incrementAndGet();
    }
}