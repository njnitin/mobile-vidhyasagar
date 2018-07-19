package com.jain.vidhyasagarsant.ui.categoryList.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.jain.vidhyasagarsant.ui.categoryList.master.CategoryItemListActivity;
import com.jain.vidhyasagarsant.R;

/**
 * An activity representing a single CategoryItem detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link CategoryItemListActivity}.
 */
public class CategoryItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryitem_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(CategoryItemDetailFragment.ARG_ITEM_ID, getIntent().getStringExtra(CategoryItemDetailFragment.ARG_ITEM_ID));
            arguments.putString(CategoryItemDetailFragment.ARG_ITEM_NAME, getIntent().getStringExtra(CategoryItemDetailFragment.ARG_ITEM_NAME));
            arguments.putString(CategoryItemDetailFragment.ARG_ITEM_DESCRIPTION, getIntent().getStringExtra(CategoryItemDetailFragment.ARG_ITEM_DESCRIPTION));


            CategoryItemDetailFragment fragment = new CategoryItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.categoryitem_detail_container, fragment)
                    .commit();
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

    /*@Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    public void onStop() {
        this.finish();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        this.finish();
        super.onDestroy();

    }
    @Override
    public void onPause ()
    {
        this.finish();
        super.onPause();

    }*/
}
