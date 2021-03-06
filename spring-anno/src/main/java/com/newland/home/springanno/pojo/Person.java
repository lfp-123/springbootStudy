package com.newland.home.springanno.pojo;

/**
 * @author ${linfengpeng}
 * @Title: Person
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/9/24 9:48
 */
public class Person  {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Person(String name, String age) {

        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
