/*
Was use to add products to the list and populate them with meaningful data
- handles data for the list item
- displaying toast alerts
 */
package com.engineers.united.unitedengineers.mFragments;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.engineers.united.unitedengineers.R;
import com.engineers.united.unitedengineers.adapter.StationListAdapter;
import com.engineers.united.unitedengineers.beans.Station;
import com.engineers.united.unitedengineers.utils.SharedPreference;


/**
 * Created by darren on 2017-11-18.
 */

public class StationListFragment extends Fragment implements
        OnItemClickListener, OnItemLongClickListener {

    public static final String ARG_ITEM_ID = "station_list";

    Activity activity;
    ListView stationListView;
    List<Station> stations;
    StationListAdapter stationListAdapter;
    SharedPreference sharedPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        sharedPreference = new SharedPreference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_station_list, container, false);
        findViewsById(view);
        setStations();
        stationListAdapter = new StationListAdapter(activity, stations);
        stationListView.setAdapter(stationListAdapter);
        stationListView.setOnItemClickListener(this);
        stationListView.setOnItemLongClickListener(this);
        return view;
    }

    private void setStations() {

        Station theEdge = new Station(1, "theEdge", "http://live.leanstream.co/CFNYFM?tunein");
        Station virginRadio  = new Station(2, "VirginRadio", "http://18653.live.streamtheworld.com/CKFMFMAAC.aac?");
        Station q107 = new Station(3, "Q107", "http://live.leanstream.co/CILQFM-MP3?tunein");
        Station z103 = new Station(4, "Z103", "http://ice66.securenetsystems.net/CIDC2");
        Station classicalFM = new Station(5, "ClassicalFM", "http://radiostream.zoomer.ca:8000/cfmo.mp3");
        Station htzFM = new Station(6, "HTZFM", "http://16803.live.streamtheworld.com/CHTZFMAAC.aac?");
        Station chumFM = new Station(7, "CHUMFM", "http://16143.live.streamtheworld.com/CHUMFMAAC_SC");
        stations = new ArrayList<Station>();
        stations.add(theEdge);
        stations.add(virginRadio);
        stations.add(z103);
        stations.add(q107);
        stations.add(classicalFM);
        stations.add(htzFM);
        stations.add(chumFM);
    }

    private void findViewsById(View view) { stationListView = (ListView) view.findViewById(R.id.list_station);}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Station station = (Station) parent.getItemAtPosition(position);
        /*Use station.postion to decide which stream is being selected then run Radio activity*/
        //Toast.makeText(activity, station.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long arg3) {

        ImageView button = (ImageView) view.findViewById(R.id.imgbtn_favorite);
        String tag = button.getTag().toString();
        if (tag.equalsIgnoreCase("grey")) {
            sharedPreference.addFavorite(activity, stations.get(position));
            Toast.makeText(activity, activity.getResources().getString(R.string.add_favr), Toast.LENGTH_SHORT).show();
            button.setTag("red");
            button.setImageResource(R.drawable.heart_red);
        } else {
            sharedPreference.removeFavorite(activity, stations.get(position));
            button.setTag("grey");
            button.setImageResource(R.drawable.heart_grey);
            Toast.makeText(activity, activity.getResources().getString(R.string.remove_favr), Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public void onResume() {
        //getActivity().setTitle(R.string.app_name);
        //getActivity().getActionBar().setTitle(R.string.app_name);
        super.onResume();
    }
}
