package com.jain.vidhyasagarsant.ui.webView;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jain.vidhyasagarsant.ui.base.BaseActivity;
import com.jain.vidhyasagarsant.utils.AppConstants;
import com.jain.vidhyasagarsant.utils.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.iamBedant.starter.utils.GoogleAnalyticsManager;

/**
 * Created by kuliza-303 on 07/06/17.
 */

public class WebViewActivity extends BaseActivity {

    @BindView(com.jain.vidhyasagarsant.R.id.webview)
    WebView mWebView;
    @BindView(com.jain.vidhyasagarsant.R.id.toolbar)
    Toolbar mToolbar;

    String mWebViewContent = "";
    private String mHeading = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jain.vidhyasagarsant.R.layout.activity_web_view);

        setUp();

        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.WEBVIEW_SCREEN_VISITED);

        DialogUtils.displayProgressDialog(this,getString(com.jain.vidhyasagarsant.R.string.loading));
        mWebView.loadDataWithBaseURL("", mWebViewContent, "text/html", "UTF-8", "");
    }

    @Override
    protected void setUp() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

        mWebViewContent = getIntent().getStringExtra(AppConstants.WEBVIEW_CONTENT);
        mHeading = getIntent().getStringExtra(AppConstants.WEBVIEW_TITLE);

        mWebView.setWebViewClient(new WebBrowser());
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        mToolbar.setTitle(mHeading);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private class WebBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            DialogUtils.cancelProgressDialog();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
