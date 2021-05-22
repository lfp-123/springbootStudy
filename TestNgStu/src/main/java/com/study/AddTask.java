package com.study;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author ${林锋鹏}
 * @Title: AddTask
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/2/9 10:10
 */
public class AddTask extends RecursiveTask<Integer> {
    private int n;
    public AddTask(int n){
        this.n = n;
    }

    @Override
    protected Integer compute() {
        // 如果 n 已经为 1，可以求得结果了
        if (n == 1) {
            System.out.println (Thread.currentThread().getName()+"join() {}"+n);
            return n;
        }

        // 将任务进行拆分(fork)
        AddTask t1 = new AddTask(n - 1);
        t1.fork();
        System.out.println(Thread.currentThread().getName()+"fork() {} + {}"+n+t1);

        // 合并(join)结果
        int result = n + t1.join();
        System.out.println(Thread.currentThread().getName()+"join() {} + {} = {}"+n+t1+result);
        return result;
    }

    @Override
    public String toString() {
        return "AddTask{" +
                "n=" + n +
                '}';
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        System.out.println(forkJoinPool.invoke(new AddTask(5)));
    }
}
