package org.dubbo.netty;

import java.util.Random;

/**
 * @author ${林锋鹏}
 * @Title: Game
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/4/1 0:42
 */
public class Game {
    public static void main(String[] args) {
        int people = 10;
        Random random = new Random();
        while (true){
            System.out.println("开始游戏");
            if(people>3){
                int i = random.nextInt(10);
                if(i>5){
                    people --;
                    System.out.println("有人阵亡，退出游戏，剩余人数为："+people);
                }else {
                    continue;
                }
            }else {
                System.out.println("人数不足3人 ，结束游戏");
                break;
            }
        }
    }
}
