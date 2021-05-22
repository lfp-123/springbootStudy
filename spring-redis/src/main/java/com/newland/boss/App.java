package com.newland.boss;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App {
   static Jedis jedis = null;
    static {

        try {
            jedis = new Jedis("10.1.8.104", 6379);
            jedis.auth("123456");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    public static void main( String[] args )
    {

        long startTime = System.currentTimeMillis();
       for (int i = 10000562; i < 10010562; i++) {
            String s = String.valueOf(i);
            String value = jedis.get(s);

 }
        long endTime = System.currentTimeMillis();
        long seconds = (endTime - startTime);
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(seconds+"毫秒");
       // System.out.println(value.toString());

    }
}
