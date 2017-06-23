package com.jain.vidhyasagarsant.ui.news;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jain.vidhyasagarsant.R;
import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;

import java.util.List;

/**
 * Created by kuliza-303 on 31/05/17.
 */

public class VerticlePagerAdapter extends PagerAdapter {

    List<CategoryItemData.Response> mList;
    Context mContext;
    LayoutInflater mLayoutInflater;
    CallBack mCallBack;

    public VerticlePagerAdapter(Context context,List<CategoryItemData.Response> response,CallBack callBack) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = response;
        mCallBack = callBack;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.content_main, container, false);

        final TextView description = (TextView) itemView.findViewById(R.id.description);
        TextView title = (TextView) itemView.findViewById(R.id.title);
        ImageView image = (ImageView) itemView.findViewById(R.id.imageView);
        Button bottomLinkClick = (Button) itemView.findViewById(R.id.bottom_link);

        Glide.with(mContext).load(mList.get(position).getLogo()).into(image);


        if (TextUtils.isEmpty(mList.get(position).getLink())){
            bottomLinkClick.setVisibility(View.GONE);
        } else {
            bottomLinkClick.setVisibility(View.VISIBLE);
        }
        description.setText(mList.get(position).getDescription());
        description.setMovementMethod(ScrollingMovementMethod.getInstance());
        title.setText(mList.get(position).getTitle());
        container.addView(itemView);

        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                description.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        description.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                description.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        bottomLinkClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (!TextUtils.isEmpty(mList.get(position).getLink())) {
                mCallBack.LaunchUrl(mList.get(position).getLink());
            } else {
                Toast.makeText(mContext, R.string.no_link_present_toast, Toast.LENGTH_SHORT).show();
            }

            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    interface CallBack{
        void LaunchUrl(String url);
    }
}
