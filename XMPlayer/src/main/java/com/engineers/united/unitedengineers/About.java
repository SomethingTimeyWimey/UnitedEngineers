package com.engineers.united.unitedengineers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.unitedengineerslogo)
                .setDescription("Created By United Engineers")
                .addItem(new Element().setTitle("Version 1.0"))
                .addWebsite("https://github.com/SomethingTimeyWimey/UnitedEngineers")
                .create();

                setContentView(aboutPage);
    }
}
