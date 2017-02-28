package com.example.administrator.apolloblechat.activity;

import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.widgets.MyTittleBar;

/**
 * Created by Apollo on 2017/2/28.
 */

public class ShowWebActivity extends BaseActivity {

    private MyTittleBar mTitle;
    private WebView mWebView;
    private String url;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_show_web;
    }

    @Override
    protected boolean fullScreen() {
        return false;
    }

    @Override
    protected boolean changeSystemBar() {
        return true;
    }

    @Override
    protected void initView(View view) {
        url = getIntent().getExtras().getString("result");
        mTitle = queryViewById(view, R.id.title_show_web);
        mWebView = queryViewById(view, R.id.webview_show_web);
        WebSettings settings = mWebView.getSettings();
        //设置支持javascript,默认是false
        settings.setJavaScriptEnabled(true);
        //使WebView支持通过手势或者缩放控制器来缩放页面,默认是true
        //该设置不影响 WebView.zoomIn()和WebView.zoomOut()
        settings.setSupportZoom(true);

        //设置使用默认的缩放控制器,默认是false
        settings.setBuiltInZoomControls(true);

        //不显示默认的+/-缩放控制View, 默认是true
        settings.setDisplayZoomControls(false);
        //设置是否自动加载图片,默认是`true`,如果设置为`false`,那么所有图片都不会被加载,包括本地图片.
        settings.setLoadsImagesAutomatically(true);

        //设置是否阻止加载网络图片,默认是`false`,如果设置为`true`,那么网络图片将不会加载.(可以先设置为true,然后再设置为false,来加快页面加载速度)
        settings.setBlockNetworkImage(false);

        //设置是否阻止加载网络资源(不仅仅是图片),默认是`false`,如果设置为`true`,那么网络上的js,css,图片等资源都不会加载
        settings.setBlockNetworkLoads(false);
        //设置渲染线程的优先级
        //该方法在 Api 18之后被废弃,优先级由WebView自己管理
        //不过任然建议将其设置为 HIGH,来提高页面渲染速度
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //设置使用 宽 的Viewpoint,默认是false
        //Android browser以及chrome for Android的设置是`true`
        //而WebView的默认设置是`false`
        //如果设置为`true`,那么网页的可用宽度为`980px`,并且可以通过 meta data来设置
        //如果设置为`false`,那么可用区域和WebView的显示区域有关.
        settings.setUseWideViewPort(true);

        //如果webview内容宽度大于显示区域的宽度,那么将内容缩小,以适应显示区域的宽度, 默认是false
        settings.setLoadWithOverviewMode(true);
        //支持H5的 application cache 的功能
        settings.setAppCacheEnabled(true);
        //设置 application cache 的存储路径(通常存储js,css,图片等)
        settings.setAppCachePath("xxx");

        //支持 H5 的session storage和local storage
        settings.setDomStorageEnabled(true);

        //支持javascript读,写db
        settings.setDatabaseEnabled(true);
        //设置js创建的db文件的路径, Api 19以后废弃,直接有webview管理
        settings.setDatabasePath("xxx");
        //设置加载资源时,如何使用cache
        //默认设置是:WebSettings.LOAD_DEFAULT
        //当WebView正常加载一个页面时,如果缓存命中且没有过期,则使用缓存数据,否则从网络加载,当WebView.goBack()时,如果缓存命中,直接使用,不会验证是否过期
        //可用的其他设置:LOAD_CACHE_ELSE_NETWORK, LOAD_NO_CACHE, LOAD_CACHE_ONLY
//        settings.setCacheModel(WebSettings.LOAD_DEFAULT);
        if (!TextUtils.isEmpty(url))
            mWebView.loadUrl(url);


    }
}
