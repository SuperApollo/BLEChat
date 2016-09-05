package com.example.administrator.apolloblechat.fragment;

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
                FragmentUtils.replace(getActivity(),R.id.ll_fragment_container,new ChatFragment());
            }
        });

        ContactAdapter contactAdapter = new ContactAdapter(getContext(), getData());
        xlv_contact.setAdapter(contactAdapter);
    }

    private List<ContactBean> getData() {
        List<ContactBean> contactBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ContactBean contactBean = null;
            if (i % 3 == 0) {
                contactBean = new ContactBean(BitmapFactory.decodeResource(getResources(), R.drawable.icon_tab_item_contact),
                        "王二小" + i, "156454" + i, "W");
            } else {
                contactBean = new ContactBean(BitmapFactory.decodeResource(getResources(), R.drawable.icon_tab_item_contact),
                        "王二小" + i, "156454" + i, "");
            }

            contactBeans.add(contactBean);
        }

        return contactBeans;
    }
}
