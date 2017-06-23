package com.jain.vidhyasagarsant.ui.imageFullScreenActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jain.vidhyasagarsant.ui.base.BaseActivity;
import com.jain.vidhyasagarsant.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.iamBedant.starter.utils.GoogleAnalyticsManager;

import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;

public class ImageFullScreenActivity extends BaseActivity implements ImageFullScreenMvpView {

    private List<CategoryItemData.Response> response;
    private int positionToStart = 0;

    @BindView(com.jain.vidhyasagarsant.R.id.vp_full_screen_image)
    ViewPager mViewPagerFullScreenImage;

    @Inject
    ImageFullScreenMvpPresenter<ImageFullScreenMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jain.vidhyasagarsant.R.layout.activity_image_full_screen);

        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.IMAGE_FULL_SCREEN_VISITED);

        setUp();
        getData();
        setViewPager();
    }

    private void setViewPager() {
        CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this);
        mViewPagerFullScreenImage.setAdapter(mCustomPagerAdapter);
        mViewPagerFullScreenImage.setCurrentItem(positionToStart);
    }

    @Override
    protected void setUp() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
    }

    public void getData() {
        response = getIntent().getParcelableArrayListExtra(AppConstants.GALLARY_RESPONSE);
        positionToStart = getIntent().getIntExtra(AppConstants.GALLARY_IMAGE_INDEX, 0);
    }

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return response.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(com.jain.vidhyasagarsant.R.layout.fragment_full_screen_image, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(com.jain.vidhyasagarsant.R.id.iv_image_full_screen);

            Glide.with(mContext).load(response.get(position).getLink())
                    .into(imageView);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}
