package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerate {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.example.administrator.greendao.bean");//前面参数为版本号后一个包名
        schema.setDefaultJavaPackageDao("com.example.administrator.greendao.dao");

        addBean(schema);//添加bean数据
        new DaoGenerator().generateAll(schema, "../BLEChat/app/src/main/java-gen");

    }

    private static void addBean(Schema schema) {
        Entity contact = schema.addEntity("Contact");//一个实体类对应一张数据库表，此处表名为 CONTACT （即类名）
        // 也可以重新命名表名
        // person.setTableName("People");
        contact.addIdProperty().primaryKey().autoincrement();//主键
        contact.addStringProperty("name").notNull();//姓名，非空
        contact.addStringProperty("sex").notNull();//性别，非空
        contact.addStringProperty("age");//年龄
        contact.addStringProperty("phoe");//电话
        contact.addStringProperty("ble");//蓝牙mac

        //聊天消息
        Entity chatBean = schema.addEntity("ChatBean");
        chatBean.addStringProperty("mac").notNull().primaryKey();//mac地址，主键
        chatBean.addStringProperty("time");//时间
        chatBean.addStringProperty("name");//姓名
        chatBean.addStringProperty("sex");//性别
        chatBean.addStringProperty("head");//头像
        chatBean.addStringProperty("content");//聊天内容
        chatBean.addBooleanProperty("from");//true表示收到的消息


    }
}
