package com.example.daysmatter.model;

/**
 * 倒数事件实体类
 * @author  sss
 * created at 2019/3/13 11:37 AM
 */
public class EventBean {
    /** 数据库自增ID */
    public int id;
    /** 事件名称 */
    public String name;
    /** 目标日 */
    public String data;
    /** 倒数本id */
    public int book_id;
    /** 是否在首页展示 */
    public int home_show;
    /** 是否在首页置顶 */
    public int home_first;
    /** 是否重复 */
    public String repeat;

    public String toString(){
        StringBuilder builder = new StringBuilder("[");
        builder.append(id).append("--");
        builder.append("事件名称：").append(name).append("--");
        builder.append("目标日：").append(data).append("--");
        builder.append("倒数本id：").append(book_id).append("--");
        builder.append("是否在首页展示：").append(home_show).append("--");
        builder.append("是否在首页置顶：").append(home_first).append("--");
        builder.append("是否重复：").append(repeat).append("]");
        return builder.toString();
    }
}
