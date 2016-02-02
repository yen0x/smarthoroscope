package com.horoscope.yenox.smarthoroscope.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.horoscope.yenox.smarthoroscope.fragments.CategoryFragment;
import com.horoscope.yenox.smarthoroscope.models.Horoscope;

/**
 * Created by yenox on 1/31/16.
 */
public class CategoryPagerAdapter extends FragmentPagerAdapter {

    private Horoscope horoscope;
    private Context context;

    public CategoryPagerAdapter(FragmentManager fm, Horoscope horoscope, Context context) {
        super(fm);
        this.horoscope = horoscope;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryFragment.newInstance(horoscope.getCategories().get(position));
    }

    @Override
    public int getCount() {
        return horoscope.getCategories().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int identifier = context.getResources().getIdentifier(horoscope.getCategories().get(position).getName(), "string", context.getPackageName());
        return context.getResources().getString(identifier);
    }
}
