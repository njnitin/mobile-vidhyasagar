package com.jain.vidhyasagarsant.ui.subCategory.master;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.iamBedant.starter.utils.GoogleAnalyticsManager;

import com.jain.vidhyasagarsant.R;
import com.jain.vidhyasagarsant.data.remote.model.subcategories.Response;
import com.jain.vidhyasagarsant.ui.base.BaseActivity;
import com.jain.vidhyasagarsant.ui.categoryList.master.CategoryItemListActivity;
import com.jain.vidhyasagarsant.ui.news.NewsCardActivity;
import com.jain.vidhyasagarsant.ui.gallery.GalleyActivity;
import com.jain.vidhyasagarsant.ui.subCategory.detail.ItemDetailActivity;
import com.jain.vidhyasagarsant.utils.AppConstants;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class SubCategoryListActivity extends BaseActivity implements SubCategoryListMvpView {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Inject
    SubCategoryListMvpPresenter<SubCategoryListMvpView> mPresenter;


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.item_list)
    RecyclerView mRecyclerView;

    String mCategoryName;
    String categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(SubCategoryListActivity.this);


        categoryId = getIntent().getExtras().getString(AppConstants.CATEGORY_ID);
        mCategoryName = getIntent().getExtras().getString(AppConstants.CATEGORY_NAME);

        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.SUB_CATEGORY_SCREEN_VISITED+"_"+categoryId);
        setUp();

        mPresenter.getCategoryData(categoryId);

    }

    @Override
    protected void setUp() {

        mToolbar.setTitle(mCategoryName);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
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

    @Override
    public void setData(List<Response> response) {
        mRecyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(response));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.SUB_CATEGORIES_LOADED);
    }

    @Override
    public void openNewsScreen(String s) {
        Intent intent = new Intent(this, NewsCardActivity.class);
        intent.putExtra(AppConstants.CATEGORY_ID, categoryId);
        intent.putExtra(AppConstants.SUB_CATEGORY_ID, s);
        startActivity(intent);
    }


    @Override
    public void openContentList(String id, String name) {
        Intent intent = new Intent(this, CategoryItemListActivity.class);
        intent.putExtra(AppConstants.CATEGORY_ID, categoryId);
        intent.putExtra(AppConstants.SUB_CATEGORY_ID, id);
        intent.putExtra(AppConstants.CATEGORY_NAME, name);
        startActivity(intent);
    }

//    public void openVideoLibrary(){
//        Intent intent = new Intent(this, VideoListActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void openGallery(String id, String name) {
        Intent intent = new Intent(this, GalleyActivity.class);
        intent.putExtra(AppConstants.CATEGORY_ID, categoryId);
        intent.putExtra(AppConstants.CATEGORY_NAME, name);
        intent.putExtra(AppConstants.SUB_CATEGORY_ID, id);
        startActivity(intent);
    }


    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Response> mValues;

        public SimpleItemRecyclerViewAdapter(List<Response> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.categoryitem_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).getName() + "");
            holder.mContentView.setVisibility(View.GONE);
            if (TextUtils.isEmpty(holder.mItem.getLogo())) {
                holder.mImageView.setVisibility(View.GONE);
            } else {
                holder.mImageView.setVisibility(View.VISIBLE);
                Glide.with(SubCategoryListActivity.this).load(holder.mItem.getLogo()).into(holder.mImageView);
            }
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mPresenter.itemClicked(holder.mItem);

                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public final ImageView mImageView;
            public Response mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mImageView = (ImageView) view.findViewById(R.id.logo);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
