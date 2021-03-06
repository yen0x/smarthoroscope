package com.horoscope.yenox.smarthoroscope;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.annimon.stream.Optional;
import com.horoscope.yenox.smarthoroscope.adapters.CategoryPagerAdapter;
import com.horoscope.yenox.smarthoroscope.fragments.CategoryFragment;
import com.horoscope.yenox.smarthoroscope.helpers.HoroscopeHelper;
import com.horoscope.yenox.smarthoroscope.models.Category;
import com.horoscope.yenox.smarthoroscope.models.Horoscope;
import com.horoscope.yenox.smarthoroscope.models.ui.CategoryContent;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class HoroscopeActivity extends AppCompatActivity
        implements CategoryFragment.OnListFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private CategoryPagerAdapter mCategoryPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);

        Intent intent = getIntent();
        String sign = intent.getStringExtra("sign");
        Optional<Horoscope> horoscope = HoroscopeHelper.retrieveHoroscope(this, sign);

        if(!horoscope.isPresent()) {
            Toast.makeText(this, getString(R.string.get_horoscope_error), Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        String displayLanguage = Locale.getDefault().getISO3Language();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale(displayLanguage));
        this.setTitle(sign + " - " + dateFormat.format(new Date()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        for (Category cat : horoscope.get().getCategories()) {
            tabLayout.addTab(tabLayout.newTab().setText(cat.getId()));
        }

        // Create the adapter that will return a fragment for each of the sections
        mCategoryPagerAdapter = new CategoryPagerAdapter(getSupportFragmentManager(), horoscope.get(), this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mCategoryPagerAdapter);
        // Keep all pages in memory
        mViewPager.setOffscreenPageLimit(3);

        // synch pager and tabs
        tabLayout.setupWithViewPager(mViewPager);
        // set pager title through getPageTitle
        tabLayout.setTabsFromPagerAdapter(mCategoryPagerAdapter);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_horoscope, menu);
        //return true;
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(CategoryContent.CategoryItem item) {
        Toast.makeText(HoroscopeActivity.this, item.name, Toast.LENGTH_SHORT).show();
    }
}
