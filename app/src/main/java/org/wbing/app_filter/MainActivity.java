package org.wbing.app_filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.wbing.app_filter.databinding.ActivityMainBinding;
import org.wbing.base.ui.impl.WAct;

public class MainActivity extends WAct<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void recycle() {

    }
}
