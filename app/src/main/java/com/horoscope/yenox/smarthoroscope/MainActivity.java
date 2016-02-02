package com.horoscope.yenox.smarthoroscope;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.horoscope.yenox.smarthoroscope.fragments.SignListFragment;
import com.horoscope.yenox.smarthoroscope.models.ui.SignContent;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity//
        implements SignListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignContent.addSignItems(Arrays.asList(getResources().getStringArray(R.array.sign_names)));
    }

    @Override
    public void onListFragmentInteraction(SignContent.SignItem item) {
        Intent intent = new Intent(this, HoroscopeActivity.class);
        intent.putExtra("sign", item.name);
        startActivity(intent);
    }
}
