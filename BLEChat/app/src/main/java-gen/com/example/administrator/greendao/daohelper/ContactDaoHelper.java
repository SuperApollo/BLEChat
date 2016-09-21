package com.example.administrator.greendao.daohelper;

import com.example.administrator.apolloblechat.base.BaseApplication;
import com.example.administrator.greendao.bean.Contact;
import com.example.administrator.greendao.dao.ContactDao;
import com.example.administrator.greendao.dao.DaoSession;

import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class ContactDaoHelper {


    private final ContactDao contactDao;
    private static volatile ContactDaoHelper contactDaoHelper;

    private ContactDaoHelper() {
        DaoSession daoSession = BaseApplication.getBaseApplication().daoSession;
        contactDao = daoSession.getContactDao();
    }

    public static ContactDaoHelper getContactDaoHelper() {
        if (null == contactDaoHelper) {
            synchronized (ContactDaoHelper.class) {
                if (null == contactDaoHelper) {
                    contactDaoHelper = new ContactDaoHelper();
                }
            }
        }
        return contactDaoHelper;
    }

    public List<Contact> getContactByName(String name) {
        List<Contact> contacts = null;
        try {
            contacts = contactDao.queryBuilder().where(ContactDao.Properties.Name.eq(name)).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public List<Contact> getContactByPhone(String phone) {
        List<Contact> contacts = null;
        try {
            contacts = contactDao.queryBuilder().where(ContactDao.Properties.Phoe.eq(phone)).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void insertOrReplace(Contact contact) {
        try {
            contactDao.insertOrReplace(contact);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = null;
        try {
            contacts = contactDao.queryBuilder().orderAsc(ContactDao.Properties.Name).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contacts;
    }

    public void deleteAllContacts() {
        try {
            contactDao.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByName(String name) {
        List<Contact> contactByName = getContactByName(name);
        if (null != contactByName && contactByName.size() > 0) {
            for (Contact contact : contactByName) {
                contactDao.delete(contact);
            }
        }
    }

    public void deleteByPhone(String phone) {
        List<Contact> contactByName = getContactByName(phone);
        if (null != contactByName && contactByName.size() > 0) {
            for (Contact contact : contactByName) {
                contactDao.delete(contact);
            }
        }
    }

}
