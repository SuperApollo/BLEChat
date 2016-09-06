package com.example.administrator.apolloblechat.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.adapter.ContactAdapter;
import com.example.administrator.apolloblechat.bean.ContactBean;
import com.example.administrator.apolloblechat.utils.FragmentUtils;
import com.example.administrator.apolloblechat.widgets.SidebarView;
import com.example.administrator.apolloblechat.widgets.XListView;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ContactFragment extends BaseFragment {

    private EditText et_contact_search;
    private TextView tv_contact_show;
    private XListView xlv_contact;
    private SidebarView sidebar_contact;
    private String[] names;
    private String name = "abc 刀白凤 丁春秋 马夫人 马五德 小翠 于光豪 巴天石 不平道人 邓百川 风波恶 " +
            "甘宝宝 公冶乾 木婉清 包不同 天狼子 太皇太后 王语嫣 乌老大 无崖子 云岛主 云中鹤 止清 白世镜 " +
            "天山童姥 本参 本观 本相 本因 出尘子 冯阿三 古笃诚 少林老僧 过彦之 兰剑 平婆婆 石清露 石嫂 " +
            "司空玄 司马林 玄慈 玄寂 玄苦 玄难 玄生 玄痛 叶二娘 左子穆 耶律莫哥 李春来 李傀儡 李秋水 刘竹庄 " +
            "祁六三 乔峰 全冠清 朴者和尚 阮星竹 许卓诚 朱丹臣 竹剑 阿碧 阿洪 阿胜 西夏宫女 阿朱 阿紫 波罗星 陈孤雁 " +
            "何望海 鸠摩智 来福儿 耶律洪基 努儿海 宋长老 苏星河 苏辙 完颜阿古打 吴长风 枯荣长老 辛双清 严妈妈 余婆婆 岳老三 " +
            "张全祥 单伯山 单季山 单叔山 郭靖 黄蓉 def 额";
    private ContactAdapter contactAdapter;
    private char firtLetter;

    @Override
    protected int getViewId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initView(View view) {
        et_contact_search = queryViewById(view, R.id.et_contact_search);
        tv_contact_show = queryViewById(view, R.id.tv_contact_show);
        xlv_contact = queryViewById(view, R.id.xlv_contact);
        sidebar_contact = queryViewById(view, R.id.sidebar_contact);

        sidebar_contact.setTextView(tv_contact_show);
        sidebar_contact.setOnLetterClickedListener(new SidebarView.OnLetterClickedListener() {
            @Override
            public void onLetterClicked(String str) {
                int positon = contactAdapter.getPositionForSection(str.charAt(0));
                xlv_contact.setSelection(positon + 1);

            }
        });

        xlv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentUtils.replace(getActivity(), R.id.ll_fragment_container, new ChatFragment());
            }
        });

        et_contact_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        names = name.split(" ");
        contactAdapter = new ContactAdapter(getContext(), getData());
        xlv_contact.setAdapter(contactAdapter);


    }

    private List<ContactBean> getData() {
        List<ContactBean> contactBeans = new ArrayList<>();
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.icon_tab_item_contact);
        for (int i = 0; i < names.length; i++) {
            ContactBean contactBean = null;

            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//大写
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//无音标
            format.setVCharType(HanyuPinyinVCharType.WITH_V);
            String[] pinyin = new String[0];
            try {
                pinyin = PinyinHelper.toHanyuPinyinStringArray(names[i].charAt(0), format);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
            if (pinyin == null) {
                firtLetter = '#';
            } else {
                firtLetter = pinyin[0].charAt(0);
            }

            try {

            } catch (Exception e) {

            }

            contactBean = new ContactBean(icon, names[i], "10086" + i, firtLetter + "");

            contactBeans.add(contactBean);
        }

        Collections.sort(contactBeans, new Comparator<ContactBean>() {
            @Override
            public int compare(ContactBean contactBean, ContactBean t1) {
                return contactBean.getFirstWord().compareTo(t1.getFirstWord());
            }
        });

        for (int i = 0; i < contactBeans.size(); i++) {
            for (int j = i + 1; j < contactBeans.size() + 1; j++) {
                if (j < contactBeans.size()) {
                    String rLetter = contactBeans.get(j).getFirstWord();
                    String lLetter = contactBeans.get(i).getFirstWord();
                    if (rLetter.equals(lLetter)) {
                        ContactBean bean = contactBeans.get(j);
                        bean.setFirstWord("");
                    }
                }

            }
        }

        return contactBeans;
    }
}
