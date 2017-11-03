package com.example.charlesanderson.shoppa;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by charlesanderson on 11/2/17.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private static int TAB_COUNT = 2;

    public MyFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DiscussionFragment();
        } else {
            return new DealFragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.Discussion);
            case 1:
                return mContext.getString(R.string.Deals);
            default:
                return null;
        }
    }

}
