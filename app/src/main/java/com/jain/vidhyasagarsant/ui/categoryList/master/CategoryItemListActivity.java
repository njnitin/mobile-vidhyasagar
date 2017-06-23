package com.jain.vidhyasagarsant.ui.categoryList.master;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jain.vidhyasagarsant.R;
import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.ui.base.BaseActivity;
import com.jain.vidhyasagarsant.ui.categoryList.detail.CategoryItemDetailActivity;
import com.jain.vidhyasagarsant.ui.categoryList.detail.CategoryItemDetailFragment;
import com.jain.vidhyasagarsant.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.iamBedant.starter.utils.GoogleAnalyticsManager;

/**
 * An activity representing a list of CategoryItems. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CategoryItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 *
 */
public class CategoryItemListActivity extends BaseActivity implements CategoryItemListMvpView,  SearchView.OnQueryTextListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Inject
    CategoryItemListMvpPresenter< CategoryItemListMvpView> mPresenter;
    String mCategoryName;
    String categoryId;
    String subCategoryId;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @BindView(R.id.categoryitem_list)
    RecyclerView mRecyclerView;
    private List<CategoryItemData.Response> mResponse;
    private SimpleItemRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryitem_list);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);

        categoryId = getIntent().getExtras().getString(AppConstants.CATEGORY_ID);
        mCategoryName = getIntent().getExtras().getString(AppConstants.CATEGORY_NAME);
        subCategoryId = getIntent().getExtras().getString(AppConstants.SUB_CATEGORY_ID);

        setUp();

        if(subCategoryId==null || subCategoryId.isEmpty()) {
            mPresenter.getCategoryItemData(categoryId);
            GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.CATEGORY_SCREEN_VISITED+"_"+categoryId);
        }else {
            mPresenter.getSubCategoryItemData(categoryId,subCategoryId);
            GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.CATEGORY_SCREEN_VISITED+"_"+categoryId+"_"+subCategoryId);
        }


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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        adapter.setFilter(mResponse);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
        return true;
    }

    @Override
    public void setData(List<CategoryItemData.Response> response) {
        mResponse = response;
        adapter = new CategoryItemListActivity.SimpleItemRecyclerViewAdapter(response);
        mRecyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.CATEGORIES_LOADED);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<CategoryItemData.Response> filteredModelList = filter(mResponse, newText);
        adapter.setFilter(filteredModelList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    private List<CategoryItemData.Response> filter(List<CategoryItemData.Response> models, String query) {
        query = query.toLowerCase();

        final List<CategoryItemData.Response> filteredModelList = new ArrayList<>();
        for (CategoryItemData.Response model : models) {
            final String text = model.getSubtitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private List<CategoryItemData.Response> mValues;
        private final List<CategoryItemData.Response> mOrignalValues;


        public SimpleItemRecyclerViewAdapter(List<CategoryItemData.Response> items) {
            mValues = items;
            mOrignalValues = items;
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
            holder.mIdView.setText(mValues.get(position).getTitle()+"");
            holder.mContentView.setText(mValues.get(position).getSubtitle()+"");
            if (TextUtils.isEmpty(holder.mItem.getLogo())){
                holder.mImageView.setVisibility(View.GONE);
            } else {
                holder.mImageView.setVisibility(View.VISIBLE);
                Glide.with(CategoryItemListActivity.this).load(holder.mItem.getLogo()).into(holder.mImageView);
            }
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(CategoryItemDetailFragment.ARG_ITEM_ID, holder.mItem.getId()+"");
                        arguments.putString(CategoryItemDetailFragment.ARG_ITEM_NAME, holder.mItem.getTitle()+"");
                        arguments.putString(CategoryItemDetailFragment.ARG_ITEM_DESCRIPTION, holder.mItem.getDescription()+"");
                        CategoryItemDetailFragment fragment = new CategoryItemDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.categoryitem_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, CategoryItemDetailActivity.class);
                        intent.putExtra(CategoryItemDetailFragment.ARG_ITEM_ID, holder.mItem.getId()+"");
                        intent.putExtra(CategoryItemDetailFragment.ARG_ITEM_NAME, holder.mItem.getTitle()+"");
                        intent.putExtra(CategoryItemDetailFragment.ARG_ITEM_DESCRIPTION, holder.mItem.getDescription()+"");

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public void setFilter(List<CategoryItemData.Response> response){
            mValues = new ArrayList<>();
            mValues.addAll(response);
            notifyDataSetChanged();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public final ImageView mImageView;
            public CategoryItemData.Response mItem;

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
}
