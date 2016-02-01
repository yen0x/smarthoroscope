package com.horoscope.yenox.smarthoroscope.adapters;

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

    public CategoryPagerAdapter(FragmentManager fm, Horoscope horoscope) {
        super(fm);
        this.horoscope = horoscope;
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
        return horoscope.getCategories().get(position).getName();
    }
}
