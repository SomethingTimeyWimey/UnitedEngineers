/*
    United Engineers
    this activity displays all the content in the about us page
 */
package com.engineers.united.unitedengineers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * Created by aldo
 */

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //CREATED AN ABOUT PAGE WITH ALL THE NECESSARY REQUIREMENTS WHICH GIVES USERS MORE INFORMATION ABOUT OUR APP
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
