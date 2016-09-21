package com.example.administrator.apolloblechat.fragment;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.adapter.ContactAdapter;
import com.example.administrator.apolloblechat.bean.ContactBean;
import com.example.administrator.apolloblechat.utils.FragmentUtils;
import com.example.administrator.apolloblechat.widgets.MyTittleBar;
import com.example.administrator.apolloblechat.widgets.SidebarView;
import com.example.administrator.apolloblechat.widgets.XListView;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

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
    private List<ContactBean> contactBeanList;
    private MyTittleBar title_contact;
    private PopupWindow popAddContactView;
    private EditText et_pop_contact_name;
    private EditText et_pop_contact_sex;
    private EditText et_pop_contact_phone;
    private EditText et_pop_contact_blemac;
    private TextView tv_pop_contact_cancel;
    private TextView tv_pop_contact_add;

    @Override
    protected int getViewId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initView(final View view) {
        et_contact_search = queryViewById(view, R.id.et_contact_search);
        tv_contact_show = queryViewById(view, R.id.tv_contact_show);
        xlv_contact = queryViewById(view, R.id.xlv_contact);
        sidebar_contact = queryViewById(view, R.id.sidebar_contact);
        title_contact = queryViewById(view, R.id.title_contact);

        title_contact.setOnRightlayoutListener(new MyTittleBar.OnRightlayoutListener() {
            @Override
            public void onRightClick() {
                if (popAddContactView != null && !popAddContactView.isShowing()) {
                    popAddContactView.showAtLocation(view, Gravity.CENTER, 0, 0);
                    backgroundAlpha(0.3f);
                }

            }
        });
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

        xlv_contact.setPullRefreshEnable(false);

        names = name.split(" ");
        contactBeanList = getData();
        contactAdapter = new ContactAdapter(mContext, contactBeanList);
        xlv_contact.setAdapter(contactAdapter);

        editSearch();
        initPop();
    }

    /**
     * 初始化添加联系人弹框
     */
    private void initPop() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_contact_pop, null);
        et_pop_contact_name = queryViewById(view, R.id.et_pop_contact_name);
        et_pop_contact_sex = queryViewById(view, R.id.et_pop_contact_sex);
        et_pop_contact_phone = queryViewById(view, R.id.et_pop_contact_phone);
        et_pop_contact_blemac = queryViewById(view, R.id.et_pop_contact_blemac);
        tv_pop_contact_cancel = queryViewById(view, R.id.tv_pop_contact_cancel);
        tv_pop_contact_add = queryViewById(view, R.id.tv_pop_contact_add);

        if (popAddContactView == null)
            popAddContactView = new PopupWindow(view);
        popAddContactView.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popAddContactView.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popAddContactView.setOutsideTouchable(true);
        popAddContactView.setFocusable(true);
        popAddContactView.setAnimationStyle(R.style.PopPreviewInOut);
        popAddContactView.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        tv_pop_contact_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popAddContactView.dismiss();

            }
        });
        tv_pop_contact_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToastUtil.toaster("添加成功");
                popAddContactView.dismiss();
            }
        });
    }

    /**
     * 搜索
     */
    private void editSearch() {
        et_contact_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<ContactBean> searchContacts;
                String str = et_contact_search.getText().toString();
                if (s != null && !"".equals(str)) {
                    searchContacts = search(str, contactBeanList);
                    reLoadList(searchContacts);
                } else {
                    reLoadList(getData());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void reLoadList(List<ContactBean> searchContacts) {
        if (searchContacts != null && searchContacts.size() > 0) {
            contactBeanList.clear();
            contactBeanList.addAll(searchContacts);
        } else {
            contactBeanList.clear();
        }
        contactAdapter.notifyDataSetChanged();
    }


    /**
     * 按号码、拼音搜索
     *
     * @param s
     * @param contactBeanList
     * @return
     */
    private List<ContactBean> search(String s, List<ContactBean> contactBeanList) {
        if (contactBeanList == null || contactBeanList.size() == 0) {
            return null;
        }
        List<ContactBean> results = new ArrayList<>();

        String pinyin = getPinyin(s);
        for (ContactBean bean : contactBeanList) {
            if (containPinyin(bean, pinyin)) {
                results.add(bean);
            }
        }


        return results;
    }

    private boolean containPinyin(ContactBean bean, String pinyin) {
        boolean flag = false;
        if (TextUtils.isEmpty(bean.getName()) || TextUtils.isEmpty(bean.getId()) || TextUtils.isEmpty(pinyin)) {
            return flag;
        }


        //简拼匹配，输入字符串长度小于6，按首字母匹配
        if (pinyin.length() < 6) {
            String firstWord = bean.getFirstWord();
            Pattern firstLettMatcher = Pattern.compile(pinyin, Pattern.CASE_INSENSITIVE);
            flag = firstLettMatcher.matcher(firstWord).find();
        }
        //全拼匹配，如果简拼已找到就不再走了
        if (!flag) {
            String namePinyin = getPinyin(bean.getName());
            Pattern nameMatcher = Pattern.compile(pinyin, Pattern.CASE_INSENSITIVE);
            flag = nameMatcher.matcher(namePinyin).find();
        }
        //按匹配号码
        if (!flag) {
            Pattern idMathcher = Pattern.compile(pinyin, Pattern.CASE_INSENSITIVE);
            flag = idMathcher.matcher(bean.getId()).find();
        }

        return flag;
    }

    private String getPinyin(String s) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//大写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//无音标
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //判断为字母或数字
            if ((c != 48) && (c != 57) && (c <= 122)) {
                sb.append(c);
            }

            try {
                String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (pinyin != null) {
                    String str = Arrays.toString(pinyin);
                    sb.append(str.substring(1, str.length() - 1));
                }

            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }

        }
        return sb.toString();
    }

    private List<ContactBean> getData() {
        List<ContactBean> contactBeans = new ArrayList<>();
        Drawable icon = getResources().getDrawable(R.mipmap.icon_tab_item_contact);
        for (int i = 0; i < names.length; i++) {
            ContactBean contactBean = null;

            String py = getPinyin(names[i]);

            if (py != null && py.length() > 0) {
                firtLetter = py.charAt(0);
            } else {
                firtLetter = '#';
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

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }
}
