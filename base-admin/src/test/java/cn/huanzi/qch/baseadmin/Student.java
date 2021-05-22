package cn.huanzi.qch.baseadmin;

/**
 * @author lfp
 * @Title: Student
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/1/27 13:52
 */
public class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
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
}
