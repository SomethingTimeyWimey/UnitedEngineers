package com.engineers.united.unitedengineers;

import android.os.Bundle;
import android.app.Activity;
import com.engineers.united.unitedengineers.List;

/**
 * Created by darren on 2017-10-16.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new List())
                .commit();
    }

}