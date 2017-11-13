package com.engineers.united.unitedengineers;

/**
 * Created by darren on 2017-10-16.
 */
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class List extends ListFragment {

    Intent i;

    String[] listitems = {
            "102.1 THE EDGE",
            "99.9 VIRGIN RADIO",
            "Q107 TORONTO ROCK",
            "Z103.5 ",
            "96.3 CLASSICAL FM",
            "97.7 HTZ FM",
            "MP3 PLAYER"
    };
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listitems));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){

        switch(position){
            case 0:
                i = new Intent(getActivity(), Activity1.class);
                break;
            case 1:
                i = new Intent(getActivity(), Activity2.class);
                break;
            case 2:
                i = new Intent(getActivity(), Activity3.class);
                break;
            case 3:
                i = new Intent(getActivity(), Activity4.class);
                break;
            case 4:
                i = new Intent(getActivity(), Activity5.class);
                break;
            case 5:
                i = new Intent(getActivity(), Activity6.class);
                break;
            case 6:
                i = new Intent(getActivity(), MusicPlayerActivity.class);
                break;
        }
        startActivity(i);
    }

}