package com.example.administrator.apolloblechat.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.widgets.SidebarView;
import com.example.administrator.apolloblechat.widgets.XListView;

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
    }
}
