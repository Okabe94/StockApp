package com.example.okabe.nexttry.main_views;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.okabe.nexttry.R;
import com.example.okabe.nexttry.adapter.SectionPagerAdapter;
import com.example.okabe.nexttry.data_base.db_classes.DataBaseHub;
import com.example.okabe.nexttry.data_base.db_classes.DbHandler;
import com.example.okabe.nexttry.settings_Page.SettingsPage;

public class MainView extends AppCompatActivity {

    public SectionPagerAdapter sectionPagerAdapter;
    public static DbHandler dbHandler;
    public static DataBaseHub baseHub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        dbHandler = new DbHandler(getApplicationContext(), null, null, 1);
        baseHub = new DataBaseHub();
        ViewPager viewPager;

        sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);

        setUpViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.stock_image);
        tabLayout.getTabAt(1).setIcon(R.drawable.deliver_image);
        tabLayout.getTabAt(2).setIcon(R.drawable.sell_image);
        tabLayout.getTabAt(3).setIcon(R.drawable.bills_image);
        tabLayout.getTabAt(4).setIcon(R.drawable.add_image);

    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addItem(new StockPage());
        adapter.addItem(new SellPage());
        adapter.addItem(new DeliverPage());
        adapter.addItem(new BillsPage());
        adapter.addItem(new SettingsPage());
        viewPager.setAdapter(adapter);
    }
}
