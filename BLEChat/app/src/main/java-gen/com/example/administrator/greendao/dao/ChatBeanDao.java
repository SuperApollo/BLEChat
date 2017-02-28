package com.example.administrator.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.administrator.greendao.bean.ChatBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHAT_BEAN".
*/
public class ChatBeanDao extends AbstractDao<ChatBean, String> {

    public static final String TABLENAME = "CHAT_BEAN";

    /**
     * Properties of entity ChatBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Mac = new Property(0, String.class, "mac", true, "MAC");
        public final static Property Time = new Property(1, String.class, "time", false, "TIME");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Sex = new Property(3, String.class, "sex", false, "SEX");
        public final static Property Head = new Property(4, String.class, "head", false, "HEAD");
        public final static Property Content = new Property(5, String.class, "content", false, "CONTENT");
        public final static Property From = new Property(6, Boolean.class, "from", false, "FROM");
    };


    public ChatBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ChatBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHAT_BEAN\" (" + //
                "\"MAC\" TEXT PRIMARY KEY NOT NULL ," + // 0: mac
                "\"TIME\" TEXT," + // 1: time
                "\"NAME\" TEXT," + // 2: name
                "\"SEX\" TEXT," + // 3: sex
                "\"HEAD\" TEXT," + // 4: head
                "\"CONTENT\" TEXT," + // 5: content
                "\"FROM\" INTEGER);"); // 6: from
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHAT_BEAN\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ChatBean entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getMac());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(2, time);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(4, sex);
        }
 
        String head = entity.getHead();
        if (head != null) {
            stmt.bindString(5, head);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(6, content);
        }
 
        Boolean from = entity.getFrom();
        if (from != null) {
            stmt.bindLong(7, from ? 1L: 0L);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ChatBean readEntity(Cursor cursor, int offset) {
        ChatBean entity = new ChatBean( //
            cursor.getString(offset + 0), // mac
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // time
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // sex
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // head
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // content
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0 // from
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ChatBean entity, int offset) {
        entity.setMac(cursor.getString(offset + 0));
        entity.setTime(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSex(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setHead(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setContent(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFrom(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(ChatBean entity, long rowId) {
        return entity.getMac();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(ChatBean entity) {
        if(entity != null) {
            return entity.getMac();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}