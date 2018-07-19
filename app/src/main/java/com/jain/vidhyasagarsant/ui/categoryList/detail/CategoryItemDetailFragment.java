package com.jain.vidhyasagarsant.ui.categoryList.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jain.vidhyasagarsant.ui.categoryList.master.CategoryItemListActivity;
import com.jain.vidhyasagarsant.R;
import com.jain.vidhyasagarsant.utils.DialogUtils;

import java.lang.reflect.InvocationTargetException;

import io.iamBedant.starter.utils.GoogleAnalyticsManager;

/**
 * A fragment representing a single CategoryItem detail screen.
 * This fragment is either contained in a {@link CategoryItemListActivity}
 * in two-pane mode (on tablets) or a {@link CategoryItemDetailActivity}
 * on handsets.
 */
public class CategoryItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_NAME = "item_name";
    public static final String ARG_ITEM_DESCRIPTION = "item_description";
    public static final String TAG = "CategoryItemDetailFram";

    /**
     * The dummy content this fragment is presenting.
     */
    String mTitle;
    String mDescription;

    WebView mWebview;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoryItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
//            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            mTitle = getArguments().getString(ARG_ITEM_NAME);
            mDescription = getArguments().getString(ARG_ITEM_DESCRIPTION);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(mTitle);
            GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.CATEGORY_ITEM_DETAILS_SCREEN_VISITED);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.categoryitem_detail, container, false);
        mWebview = (WebView) rootView.findViewById(R.id.webview);

        mWebview.setWebViewClient(new WebBrowser());
        mWebview.getSettings().setLoadsImagesAutomatically(true);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.getSettings().setSupportMultipleWindows(true);
        mWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        DialogUtils.displayProgressDialog(getActivity(),getString(R.string.loading));
        mWebview.loadDataWithBaseURL("", mDescription, "text/html", "UTF-8", "");
        // Show the dummy content as text in a TextView.

        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.CATEGORY_ITEM_DETAILS_LOADED);

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mWebview.onPause();
        mWebview.pauseTimers();

        try {
            Class.forName("android.webkit.WebView")
                    .getMethod("onPause", (Class[]) null)
                    .invoke(mWebview, (Object[]) null);

        } catch(ClassNotFoundException cnfe) {
            Log.e(TAG, "Runtime exception while getting specialized handlers");
        } catch(NoSuchMethodException nsme) {
            Log.e(TAG, "Runtime exception while getting specialized handlers");
        } catch(InvocationTargetException ite) {
            Log.e(TAG, "Runtime exception while getting specialized handlers");
        } catch (IllegalAccessException iae) {
            Log.e(TAG, "Runtime exception while getting specialized handlers");
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mWebview.resumeTimers();
        mWebview.onResume();
    }

    @Override
    public void onDestroy() {
        mWebview.destroy();
        mWebview = null;
        super.onDestroy();
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

//        @Override
//        public void onPageCommitVisible(WebView view, String url) {
//            super.onPageCommitVisible(view, url);
//
//        }

//        @Override
//        public void onPause() {
//            // TODO Auto-generated method stub
//            super.onPause();
//            mWebview.onPause();
//        }
    }
}
