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
                .setDescription(getString(R.string.created_by))
                .addItem(new Element().setTitle(getString(R.string.version_num)))
                .addWebsite(getString(R.string.website))
                .create();

                setContentView(aboutPage);
    }
}
