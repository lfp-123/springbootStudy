package com.newland.boss.springboottoken.pojo;

/**
 * @author lfp
 * @Title: User
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/15 16:03
 */
public class User {
    private String id;
    private String name;
    private int timeout;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", timeout=" + timeout +
                '}';
    }
}
