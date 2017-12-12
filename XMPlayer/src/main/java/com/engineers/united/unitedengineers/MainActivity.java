/*
UNITED ENGINEERS
Handles Bottom navigation bar, and our menu items
Entry point for the entire app
*/
package com.engineers.united.unitedengineers;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.engineers.united.unitedengineers.mFragments.FavoritesFragment;
import com.engineers.united.unitedengineers.mFragments.StationListFragment;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{
    AHBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigation= (AHBottomNavigation) findViewById(R.id.myBottomNavigation_ID);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();
    }
    private void createNavItems()
    {
        //CREATE ITEMS
        AHBottomNavigationItem radioItem=new AHBottomNavigationItem(R.string.Radio,R.drawable.radio,R.color.colorBottomNavigationAccent);
        AHBottomNavigationItem favoritesItem=new AHBottomNavigationItem(R.string.Favorites,R.drawable.favorites,R.color.colorBottomNavigationAccent);

        //ADD ITEMS TO BAR
        bottomNavigation.addItem(radioItem);
        bottomNavigation.addItem(favoritesItem);

        //PROPERTIES
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#191919"));
        bottomNavigation.setCurrentItem(0);
    }
    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if(position==0)
        {
            //RadioFragment radioFragment=new RadioFragment();
            StationListFragment stationFragment = new StationListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,stationFragment).commit();
        }else if(position==1)
        {
            FavoritesFragment favoritesFragment=new FavoritesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,favoritesFragment).commit();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/Up button, so long
        //as you specify a parent activity in AndroidManifest.xml.

        Intent bar_intent = null;


        switch (item.getItemId()) {

            case R.id.Help:
                bar_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tunein.com/radio/local/"));
                startActivity(bar_intent);
                break;

            case R.id.MP3Player:
                Intent intent = new Intent(MainActivity.this, MusicPlayerActivity.class);
                startActivity(intent);
                break;

            case R.id.GroupName:
                Toast.makeText(this,"United Engineers-Welcome to Our Radio App!!!", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}