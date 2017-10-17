package com.engineers.united.unitedengineers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.engineers.united.unitedengineers.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.menu_radio:

                    setTitle("Page One");
                    PageOne fragment = new PageOne();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment, "FragmentName");
                    fragmentTransaction.commit();
                    return true;

                case R.id.menu_favorites:

                    setTitle("Page Two");
                    PageTwo fragment2 = new PageTwo();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.content, fragment2, "FragmentName");
                    fragmentTransaction2.commit();
                    return true;

                case R.id.menu_settings:
                    setTitle("Page Three");
                    PageThree fragment3 = new PageThree();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.content, fragment3, "FragmentName");
                    fragmentTransaction3.commit();
                    return true;

                case R.id.menu_pin:

                    setTitle("Page Four");
                    PageFour fragment4 = new PageFour();
                    FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction4.replace(R.id.content, fragment4, "FragmentName");
                    fragmentTransaction4.commit();
                    return true;
            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new List())
                .commit();
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


            case R.id.GroupName:
                Toast.makeText(this,"United Engineers-Welcome to Our Radio App!!!", Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);


    }
}