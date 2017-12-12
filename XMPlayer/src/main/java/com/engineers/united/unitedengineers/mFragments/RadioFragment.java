/*
* old java file for playing radio stations and linking to the MP3 player
* */
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
import com.engineers.united.unitedengineers.MusicPlayerActivity;
import com.engineers.united.unitedengineers.R;
import com.engineers.united.unitedengineers.RadioActivity;

public class RadioFragment extends ListFragment {

    Intent i;
    String theEdge = "http://live.leanstream.co/CFNYFM?tunein";
    String virginRadio = "http://16143.live.streamtheworld.com/CKFMFMAAC_SC";
    String torontoRock = "http://live.leanstream.co/CILQFM-MP3?tunein";
    String z103 = "http://ice66.securenetsystems.net/CIDC2";
    String classicalFM = "http://65.19.131.138/mzmedia-cfmzfmaac-ibc2?session-id=1361203433&source=tunein";
    String htzFM = "http://16803.live.streamtheworld.com/CHTZFMAAC.aac?";
    String chumFM = "http://16143.live.streamtheworld.com/CHUMFMAAC_SC";

    String[] listitems = {
            "102.1 THE EDGE",
            "99.9 VIRGIN RADIO",
            "Q107 TORONTO ROCK",
            "Z103.5 ",
            "96.3 CLASSICAL FM",
            "97.7 HTZ FM",
            "104.5 CHUM FM",
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
                i = new Intent(getActivity(), RadioActivity.class);
                i.putExtra("link", theEdge);
                break;
            case 1:
                i = new Intent(getActivity(), RadioActivity.class);
                i.putExtra("link", virginRadio);
                break;
            case 2:
                i = new Intent(getActivity(), RadioActivity.class);
                i.putExtra("link", torontoRock);
                break;
            case 3:
                i = new Intent(getActivity(), RadioActivity.class);
                i.putExtra("link", z103);
                break;
            case 4:
                i = new Intent(getActivity(), RadioActivity.class);
                i.putExtra("link", classicalFM);
                break;
            case 5:
                i = new Intent(getActivity(), RadioActivity.class);
                i.putExtra("link", htzFM);
                break;
            case 6:
                i = new Intent(getActivity(), RadioActivity.class);
                i.putExtra("link", chumFM);
                break;
            case 7:
                i = new Intent(getActivity(), MusicPlayerActivity.class);
                break;
        }
        startActivity(i);
    }

}