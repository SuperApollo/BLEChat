package com.example.administrator.apolloblechat.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ContactFragment extends BaseFragment {

    private EditText et_contact_search;
    private TextView tv_contact_show;
    private XListView xlv_contact;
    private SidebarView sidebar_contact;
    private String[] names = new String[]{
            "刀白凤", "丁春秋", 马夫人 马五德 小翠  于光豪 巴天石 不平道人   邓百川 风波恶 甘宝宝 公冶乾 木婉清 包不同 天狼子 太皇太后   王语嫣 乌老大 无崖子 云岛主 云中鹤 止清  白世镜 天山童姥   本参  本观  本相  本因  出尘子 冯阿三 古笃诚 少林老僧   过彦之 兰剑  平婆婆 石清露 石嫂  司空玄 司马林 玄慈    玄寂  玄苦  玄难  玄生  玄痛  叶二娘 左子穆 耶律莫哥   李春来 李傀儡 李秋水 刘竹庄 祁六三 乔峰  全冠清 朴者和尚   阮星竹 许卓诚 朱丹臣 竹剑  阿碧  阿洪  阿胜  西夏宫女   阿朱  阿紫  波罗星 陈孤雁 何望海 鸠摩智 来福儿 耶律洪基   努儿海 宋长老 苏星河 苏辙  完颜阿古打   吴长风 枯荣长老   辛双清 严妈妈 余婆婆 岳老三 张全祥 单伯山 单季山 单叔山
    };

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

            }
        });

        xlv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentUtils.replace(getActivity(), R.id.ll_fragment_container, new ChatFragment());
            }
        });

        ContactAdapter contactAdapter = new ContactAdapter(getContext(), getData());
        xlv_contact.setAdapter(contactAdapter);
    }

    private List<ContactBean> getData() {
        List<ContactBean> contactBeans = new ArrayList<>();
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.icon_tab_item_contact);
        for (int i = 0; i < 10; i++) {
            ContactBean contactBean = null;
            if (i % 3 == 0) {
                contactBean = new ContactBean(icon, "王二小" + i, "156454" + i, "W");
            } else {
                contactBean = new ContactBean(icon, "王二小" + i, "156454" + i, "");
            }

            contactBeans.add(contactBean);
        }

        return contactBeans;
    }
}
