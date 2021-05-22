/**
 * @author ${林锋鹏}
 * @Title: ChopTick
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/2/7 17:06
 */
public class ChopTick {

    private String username;

    public  ChopTick(String username){
        this.username = username;
    }
    @Override
    public String toString() {
        return "筷子"+"{ "+username+" }";
    }
}
