package com.engineers.united.unitedengineers.mFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.engineers.united.unitedengineers.Activity1;
import com.engineers.united.unitedengineers.Activity2;
import com.engineers.united.unitedengineers.Activity3;
import com.engineers.united.unitedengineers.Activity4;
import com.engineers.united.unitedengineers.Activity5;
import com.engineers.united.unitedengineers.Activity6;
import com.engineers.united.unitedengineers.MusicPlayerActivity;
import com.engineers.united.unitedengineers.R;
/**
 * Created by Oclemmy on 5/10/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class RadioFragment extends ListFragment {

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listfragment, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.list);
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listitems));

        return rootView;
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