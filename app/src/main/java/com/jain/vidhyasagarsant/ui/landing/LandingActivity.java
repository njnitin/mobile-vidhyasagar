package com.jain.vidhyasagarsant.ui.landing;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jain.vidhyasagarsant.R;
import com.jain.vidhyasagarsant.data.local.json.JsonParserHelper;
import com.jain.vidhyasagarsant.data.remote.model.Category;
import com.jain.vidhyasagarsant.ui.base.BaseActivity;
import com.jain.vidhyasagarsant.ui.categoryList.master.CategoryItemListActivity;
import com.jain.vidhyasagarsant.ui.contactUs.ContactUsActivity;
import com.jain.vidhyasagarsant.ui.developerProfile.DeveloperActivity;
import com.jain.vidhyasagarsant.ui.gallery.GalleyActivity;
import com.jain.vidhyasagarsant.ui.news.NewsCardActivity;
import com.jain.vidhyasagarsant.ui.subCategory.master.SubCategoryListActivity;
import com.jain.vidhyasagarsant.ui.videoList.VideoListActivity;
import com.jain.vidhyasagarsant.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.iamBedant.starter.utils.GoogleAnalyticsManager;

public class LandingActivity extends BaseActivity implements LandingMvpView, NavigationView.OnNavigationItemSelectedListener {

    @BindView(com.jain.vidhyasagarsant.R.id.toolbar)
    Toolbar toolbar;
    @BindView(com.jain.vidhyasagarsant.R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(com.jain.vidhyasagarsant.R.id.item_list)
    RecyclerView mRecyclerView;

    @Inject
    LandingMvpPresenter<LandingMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.LANDING_SCREEN_VISITED);
        setUp();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.jain.vidhyasagarsant.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == com.jain.vidhyasagarsant.R.id.nav_home) {
        } else if (id == com.jain.vidhyasagarsant.R.id.nav_guru_sangh) {
            openSubcategoryScreen("1",getString(com.jain.vidhyasagarsant.R.string.vidya_));
        } else if (id == com.jain.vidhyasagarsant.R.id.nav_ahimsa_news) {
            openNewsScreen("2");
        } else if (id == com.jain.vidhyasagarsant.R.id.nav_chalisha) {
            openSubcategoryScreen("12",getString(com.jain.vidhyasagarsant.R.string.chalisa));
        } else if (id == com.jain.vidhyasagarsant.R.id.nav_photos) {
            openGallery("10","Gallery");
        } else if (id== com.jain.vidhyasagarsant.R.id.nav_videos){
            openVideoLibrary("11","Videos");
        } else if (id == com.jain.vidhyasagarsant.R.id.nav_bhajan) {
            openContentList("5","भजन");
        } else if (id == com.jain.vidhyasagarsant.R.id.nav_aarti) {
            openContentList("3","आरती");
        } else if (id == com.jain.vidhyasagarsant.R.id.nav_share) {
            shareArti();
        } else if (id == com.jain.vidhyasagarsant.R.id.nav_developer_profile) {
            Intent intent = new Intent(this, DeveloperActivity.class);
            startActivity(intent);
        } else if (id == com.jain.vidhyasagarsant.R.id.nav_contact_us) {
            Intent intent = new Intent(this, ContactUsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(com.jain.vidhyasagarsant.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void shareArti() {
        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = getString(com.jain.vidhyasagarsant.R.string.whats_app_share_message);

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this,getString(com.jain.vidhyasagarsant.R.string.whats_app_notpresent_message), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(LandingActivity.this);
        JsonParserHelper.getInstance().setMyContext(this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.jain.vidhyasagarsant.R.string.navigation_drawer_open, com.jain.vidhyasagarsant.R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(com.jain.vidhyasagarsant.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        apiCall();
    }


    private void apiCall() {
        mPresenter.getCategoryCollection();
    }

    @Override
    public void setData(List<Category.Response> response) {
        final GridLayoutManager mGridLyoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mGridLyoutManager);
        mRecyclerView.setAdapter(new CategoryRecyclerViewAdapter(response));
        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.LANDING_SCREEN_CATEGORY_LOADED);
    }

    @Override
    public void openNewsScreen(String id) {
        Intent intent = new Intent(this, NewsCardActivity.class);
        intent.putExtra(AppConstants.CATEGORY_ID,id);
        startActivity(intent);
    }

    @Override
    public void openContentList(String id, String name) {
        Intent intent = new Intent(this, CategoryItemListActivity.class);
        intent.putExtra(AppConstants.CATEGORY_ID, id);
        intent.putExtra(AppConstants.CATEGORY_NAME,name);
        startActivity(intent);
    }

    @Override
    public void openVideoLibrary(String id, String name){
        Intent intent = new Intent(this, VideoListActivity.class);
        intent.putExtra(AppConstants.CATEGORY_ID, id);
        intent.putExtra(AppConstants.CATEGORY_NAME,name);
        startActivity(intent);
    }

    @Override
    public void openGallery(String id, String name) {
        Intent intent = new Intent(this, GalleyActivity.class);
        intent.putExtra(AppConstants.CATEGORY_ID, id);
        intent.putExtra(AppConstants.CATEGORY_NAME,name);
        startActivity(intent);
    }

    @Override
    public void openSubcategoryScreen(String id, String name) {
        Intent intent = new Intent(LandingActivity.this, SubCategoryListActivity.class);
        intent.putExtra(AppConstants.CATEGORY_ID, id);
        intent.putExtra(AppConstants.CATEGORY_NAME,name);
        startActivity(intent);
    }

    private class CategoryRecyclerViewAdapter extends
            RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

        private final List<Category.Response> mValues;

        public CategoryRecyclerViewAdapter(List<Category.Response> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(com.jain.vidhyasagarsant.R.layout.category_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mItem = mValues.get(position);

            holder.mNameView.setText(mValues.get(position).getDispalyName() + "");

            if(mValues.get(position).getLogo()!=null && !mValues.get(position).getLogo().trim().isEmpty()) {
                Glide.with(holder.mImageView.getContext())
                        .load(mValues.get(position).getLogo())
                        .placeholder(com.jain.vidhyasagarsant.R.drawable.ic_jainism)
                        .into(holder.mImageView);
            }else {
                Glide.with(holder.mImageView.getContext())
                        .load(com.jain.vidhyasagarsant.R.drawable.side_nav_bar)
                        .placeholder(com.jain.vidhyasagarsant.R.drawable.ic_jainism)
                        .into(holder.mImageView);
            }

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mPresenter.dashboardClicked(mValues.get(position));

                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            public View mView;
            public TextView mNameView;
            public ImageView mImageView;

            public Category.Response mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mNameView = (TextView) view.findViewById(com.jain.vidhyasagarsant.R.id.category_name);
                mImageView = (ImageView) view.findViewById(com.jain.vidhyasagarsant.R.id.category_image);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mNameView.getText() + "'";
            }
        }

    }
}
