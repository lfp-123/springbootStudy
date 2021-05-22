package com.newland.boss.redis.springboofredis.pojo;

/**
 * @author ${linfengpeng}
 * @Title: Person
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/11/4 16:51
 */
public class Person {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
