package org.dubbo.netty;

/**
 * @author ${林锋鹏}
 * @Title: Card
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/4/1 0:40
 */
public class Card {
    private String face;
    private String suit;

    public Card(String face,String suit){
        this.face = face;
        this.suit = suit;
    }

    protected String getFace() {
        return face;
    }

    protected String getSuit() {
        return suit;
    }
}
