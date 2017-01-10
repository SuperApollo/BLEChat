package com.example.administrator.greendao.daohelper;

import com.example.administrator.apolloblechat.base.BaseApplication;
import com.example.administrator.greendao.bean.ChatBean;
import com.example.administrator.greendao.dao.ChatBeanDao;
import com.example.administrator.greendao.dao.DaoSession;

import java.util.List;

/**
 * Created by zayh_yf20160909 on 2017/1/10.
 */

public class ChatBeanDaoHelper {
    private ChatBeanDao mChatBeanDao;
    private static ChatBeanDaoHelper mChatBeanDaoHelper;

    public ChatBeanDaoHelper() {
        DaoSession daoSession = BaseApplication.getBaseApplication().daoSession;
        mChatBeanDao = daoSession.getChatBeanDao();
    }

    public static ChatBeanDaoHelper getmChatBeanDaoHelper() {
        if (mChatBeanDaoHelper == null) {
            synchronized (ChatBeanDaoHelper.class) {
                if (mChatBeanDaoHelper == null)
                    mChatBeanDaoHelper = new ChatBeanDaoHelper();
            }
        }
        return mChatBeanDaoHelper;
    }

    /**
     * 增改
     *
     * @param contact
     */
    public void insertOrReplace(ChatBean contact) {
        try {
            mChatBeanDao.insertOrReplace(contact);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据聊天人姓名删除
     *
     * @param name
     */
    public void deleteByName(String name) {
        List<ChatBean> chatBeanByName = getChatBeanByName(name);
        if (null != chatBeanByName && chatBeanByName.size() > 0) {
            for (ChatBean bean : chatBeanByName) {
                mChatBeanDao.delete(bean);
            }
        }
    }

    /**
     * 根据聊天人蓝牙mac删除
     *
     * @param mac
     */
    public void deleteByMac(String mac) {
        List<ChatBean> chatBeanByName = getChatBeanByName(mac);
        if (null != chatBeanByName && chatBeanByName.size() > 0) {
            for (ChatBean bean : chatBeanByName) {
                mChatBeanDao.delete(bean);
            }
        }
    }

    /**
     * 删除所有聊天信息
     */
    public void deleteAllChatBeans() {
        try {
            mChatBeanDao.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据聊天人的姓名查询
     *
     * @param name
     * @return
     */
    public List<ChatBean> getChatBeanByName(String name) {
        List<ChatBean> contacts = null;
        try {
            contacts = mChatBeanDao.queryBuilder().where(ChatBeanDao.Properties.Name.eq(name)).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    /**
     * 根据聊天人蓝牙mac地址查询
     *
     * @param mac
     * @return
     */
    public List<ChatBean> getChatBeanByMac(String mac) {
        List<ChatBean> contacts = null;
        try {
            contacts = mChatBeanDao.queryBuilder().where(ChatBeanDao.Properties.Mac.eq(mac)).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    /**
     * 查询所有聊天信息
     *
     * @return
     */
    public List<ChatBean> getAllChatBeans() {
        List<ChatBean> chatBeans = null;
        try {
            chatBeans = mChatBeanDao.queryBuilder().orderAsc(ChatBeanDao.Properties.Name).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chatBeans;
    }


}
