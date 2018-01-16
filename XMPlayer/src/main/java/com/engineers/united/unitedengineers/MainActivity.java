/*
UNITED ENGINEERS
Handles Bottom navigation bar, and our menu items
Entry point for the entire app
*/
package com.engineers.united.unitedengineers;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.engineers.united.unitedengineers.mFragments.FavouriteListFragment;
import com.engineers.united.unitedengineers.mFragments.StationListFragment;

import java.util.Locale;

/**
 * Created by aldo
 */

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{
    AHBottomNavigation bottomNavigation;
    //CREATED THE BOTTOM NAVIGATION TOOLBAR USING THE ACTIVITY_MAIN LAYOUT FILE

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
        //CREATE ITEMS TO DISPLAY ON BOTTOM NAVIGATION BAR
        AHBottomNavigationItem radioItem=new AHBottomNavigationItem(R.string.Radio,R.drawable.radio,R.color.colorBottomNavigationAccent);
        AHBottomNavigationItem favoritesItem=new AHBottomNavigationItem(R.string.Favorites,R.drawable.favorites,R.color.colorBottomNavigationAccent);
        AHBottomNavigationItem musicplayerItem=new AHBottomNavigationItem(R.string.MusicPlayer,R.drawable.mp3player,R.color.colorBottomNavigationAccent);


        //ADD ITEMS TO BOTTOM NAVIGATION BAR
        bottomNavigation.addItem(radioItem);
        bottomNavigation.addItem(favoritesItem);
        bottomNavigation.addItem(musicplayerItem);

        //PROPERTIES GIVEN TO THE BOTTOM NAVIGATION BAR ICONS
        bottomNavigation.setAccentColor(ContextCompat.getColor(MainActivity.this, R.color.LightGolden));
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor(getString(R.string.backgroundColorString)));
        bottomNavigation.setCurrentItem(0);
    }
    //CREATED onTabSelected WHICH ALLOWS MOVEMENT BETWEEN FRAGMENTS. IF ON SELECTED FRAGMENT, THE OTHER FRAGMENTS
    //WILL TURN GREY. I WAS ALSO ABLE TO MOVE THE MP3 ACTIVITY AS A FRAGMENT IN OUR BOTTOM NAVIGATION BAR.
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
    //CREATED MENU FOR OUR TOOLBAR IN ORDER TO HAVE THE ABOUT PAGE
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }
    //CREATED ON BACK PRESSED WHICH GIVES THE USER A DIALOG BOX CONFIRMING IF THEY WANT TO EXIT THE APP OR NOT
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getString(R.string.closing_title))
                .setMessage(getString(R.string.closing_message))
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton(getString(R.string.no), null)
                .show();
    }
    //CREATED LOGIC BEHIND ABOUT PAGE. WHEN SELECTED, IT WILL GO TO THE APPROPRIATE CLASS AND DISPLAY THE
    //APPROPRIATE FILE
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/Up button, so long
        //as you specify a parent activity in AndroidManifest.xml.

        Intent bar_intent = null;


        switch (item.getItemId()) {

            case R.id.About:
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
                break;

            case R.id.en:
                Locale locale = new Locale(getString(R.string.en));
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(this, getString(R.string.translate_english), Toast.LENGTH_LONG).show();
                recreate();
                break;

            case R.id.fr:
                Locale locale2 = new Locale(getString(R.string.fr));
                Locale.setDefault(locale2);
                Configuration config2 = new Configuration();
                config2.locale = locale2;
                getBaseContext().getResources().updateConfiguration(config2, getBaseContext().getResources().getDisplayMetrics());

                Toast.makeText(this, getString(R.string.translate_french), Toast.LENGTH_LONG).show();
                recreate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
