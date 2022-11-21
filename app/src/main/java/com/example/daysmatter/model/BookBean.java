package com.example.daysmatter.model;

/**
 * 倒数本实体类
 * @author  sss
 * created at 2019/3/13 11:34 AM
 */
public class BookBean {
    /** 数据库自增ID */
    public int id;
    /** 倒数本名 */
    public String name;
    /** 图标 */
    public int icon;
    /** 封面 */
    public int cover;

    /**
     * 转化成字符串
     * @return
     */
    public String toString(){
        StringBuilder builder = new StringBuilder("[");
        builder.append(id).append("--");
        builder.append("倒数本名：").append(name).append("--");
        builder.append("图标：").append(icon).append("--");
        builder.append("封面：").append(cover).append("]");
        return builder.toString();
    }
}
