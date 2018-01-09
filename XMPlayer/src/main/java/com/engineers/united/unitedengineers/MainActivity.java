/*
UNITED ENGINEERS
Handles Bottom navigation bar, and our menu items
Entry point for the entire app
*/
package com.engineers.united.unitedengineers;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.engineers.united.unitedengineers.mFragments.FavouriteListFragment;
import com.engineers.united.unitedengineers.mFragments.StationListFragment;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{
    AHBottomNavigation bottomNavigation;
    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigation= (AHBottomNavigation) findViewById(R.id.myBottomNavigation_ID);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();

        //FINDING THE DATABASE WITH DATABASEHELPER CLASS
        DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        c = myDbHelper.query("EMP_TABLE", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                Toast.makeText(MainActivity.this,
                        "_id: " + c.getString(0) + "\n" +
                                "E_NAME: " + c.getString(1) + "\n" +
                                "E_LINK: " + c.getString(2) + "\n" +
                                "E_DESCRI:  " + c.getString(3),
                        Toast.LENGTH_LONG).show();
            } while (c.moveToNext());
        }
    }
    private void createNavItems()
    {
        //CREATE ITEMS
        AHBottomNavigationItem radioItem=new AHBottomNavigationItem(R.string.Radio,R.drawable.radio,R.color.colorBottomNavigationAccent);
        AHBottomNavigationItem favoritesItem=new AHBottomNavigationItem(R.string.Favorites,R.drawable.favorites,R.color.colorBottomNavigationAccent);
        AHBottomNavigationItem musicplayerItem=new AHBottomNavigationItem(R.string.MusicPlayer,R.drawable.mp3player,R.color.colorBottomNavigationAccent);


        //ADD ITEMS TO BAR
        bottomNavigation.addItem(radioItem);
        bottomNavigation.addItem(favoritesItem);
        bottomNavigation.addItem(musicplayerItem);

        //PROPERTIES
        bottomNavigation.setAccentColor(ContextCompat.getColor(MainActivity.this, R.color.LightGolden));
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#800000"));
        bottomNavigation.setCurrentItem(0);
    }
    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if(position==0)
        {
            StationListFragment stationFragment = new StationListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,stationFragment).commit();
        }else if(position==1)
        {
            //FavoritesFragment favoritesFragment=new FavoritesFragment();
            FavouriteListFragment favouritesFragment=new FavouriteListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,favouritesFragment).commit();
        }else if(position==2) {
            Intent intent = new Intent(MainActivity.this, MusicPlayerActivity.class);
            startActivity(intent);
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
                Toast.makeText(this, R.string.Greatings,Toast.LENGTH_LONG).show();
                break;

            /*case R.id.MusicPlayer:
                Intent intent = new Intent(MainActivity.this, MusicPlayerActivity.class);
                startActivity(intent);
                break;
            */

            case R.id.GroupName:
                Toast.makeText(this,"XMPlayer Creators", Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}