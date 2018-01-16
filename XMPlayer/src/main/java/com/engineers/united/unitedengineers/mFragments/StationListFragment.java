/*
UNITED ENGINEERS
Was use to add products to the list and populate them with meaningful data
- handles data for the list item
- displaying toast alerts
*/
package com.engineers.united.unitedengineers.mFragments;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
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
import com.engineers.united.unitedengineers.RadioActivity;
import com.engineers.united.unitedengineers.adapter.StationListAdapter;
import com.engineers.united.unitedengineers.beans.Station;
import com.engineers.united.unitedengineers.utils.SharedPreference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by darren on 2017-11-18.
 */

public class StationListFragment extends Fragment implements OnItemClickListener, OnItemLongClickListener {

    public static final String ARG_ITEM_ID = "station_list";

    public static String news_680NAME, news_680LINK, news_680DESCRIPTION, news_680URL;
    public static String boomFMNAME, boomFMLINK, boomFMDESCRIPTION, boomFMURL;
    public static String chfiNAME, chfiLINK, chfiDESCRIPTION, chfiURL;
    public static String choqFMNAME, choqFMLINK, choqFMDESCRIPTION, choqFMURL;
    public static String chumFMNAME, chumFMLINK, chumFMDESCRIPTION, chumFMURL;
    public static String classicalFMNAME, classicalFMLINK, classicalFMDESCRIPTION, classicalFMURL;
    public static String htzFMNAME, htzFMLINK, htzFMDESCRIPTION, htzFMURL;
    public static String indieFMNAME, indieFMLINK, indieFMDESCRIPTION, indieFMURL;
    public static String jazzFMNAME, jazzFMLINK, jazzFMDESCRIPTION, jazzFMURL;
    public static String kissFMNAME, kissFMLINK, kissFMDESCRIPTION, kissFMURL;
    public static String newsTalkNAME, newsTalkLINK, newsTalkDESCRIPTION, newsTalkURL;
    public static String q107NAME, q107LINK, q107DESCRIPTION, q107URL;
    public static String virginRadioNAME, virginRadioLINK, virginRadioDESCRIPTION, virginRadioURL;
    public static String z103NAME, z103LINK, z103DESCRIPTION, z103URL;
    public static String theEdgeNAME, theEdgeLINK, theEdgeDESCRIPTION, theEdgeURL;
    Intent i;
    Activity activity;

    //This is where our data is going to end up
    ListView stationListView;
    List<Station> stations;
    //Which then gets passed to this adapter
    StationListAdapter stationListAdapter;

    SharedPreference sharedPreference;

    //DATABASE REFERENCES
    private FirebaseDatabase mDatabase;
    private DatabaseReference reference;

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

        //Intializing those above references
        mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference(getString(R.string.stations));

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //A lot of repeated code to read in all our strings from our Firebase Database
                //This should be done in a for loop...

                if (dataSnapshot.exists()){

                        news_680NAME = dataSnapshot.child("680NEWS").child("Name").getValue().toString();
                        news_680LINK = dataSnapshot.child("680NEWS").child("Link").getValue().toString();
                        news_680DESCRIPTION = dataSnapshot.child("680NEWS").child("Description").getValue().toString();
                        news_680URL = dataSnapshot.child("680NEWS").child("URL").getValue().toString();


                        boomFMNAME = dataSnapshot.child("BOOMFM").child("Name").getValue().toString();
                        boomFMLINK = dataSnapshot.child("BOOMFM").child("Link").getValue().toString();
                        boomFMDESCRIPTION = dataSnapshot.child("BOOMFM").child("Description").getValue().toString();
                        boomFMURL = dataSnapshot.child("BOOMFM").child("URL").getValue().toString();


                        chfiNAME = dataSnapshot.child("CHFI").child("Name").getValue().toString();
                        chfiLINK = dataSnapshot.child("CHFI").child("Link").getValue().toString();
                        chfiDESCRIPTION = dataSnapshot.child("CHFI").child("Description").getValue().toString();
                        chfiURL = dataSnapshot.child("CHFI").child("URL").getValue().toString();


                        choqFMNAME = dataSnapshot.child("CHOQFM").child("Name").getValue().toString();
                        choqFMLINK = dataSnapshot.child("CHOQFM").child("Link").getValue().toString();
                        choqFMDESCRIPTION = dataSnapshot.child("CHOQFM").child("Description").getValue().toString();
                        choqFMURL = dataSnapshot.child("CHOQFM").child("URL").getValue().toString();


                        chumFMNAME = dataSnapshot.child("CHUMFM").child("Name").getValue().toString();
                        chumFMLINK = dataSnapshot.child("CHUMFM").child("Link").getValue().toString();
                        chumFMDESCRIPTION = dataSnapshot.child("CHUMFM").child("Description").getValue().toString();
                        chumFMURL = dataSnapshot.child("CHUMFM").child("URL").getValue().toString();


                        classicalFMNAME = dataSnapshot.child("ClassicalFM").child("Name").getValue().toString();
                        classicalFMLINK = dataSnapshot.child("ClassicalFM").child("Link").getValue().toString();
                        classicalFMDESCRIPTION = dataSnapshot.child("ClassicalFM").child("Description").getValue().toString();
                        classicalFMURL = dataSnapshot.child("ClassicalFM").child("URL").getValue().toString();


                        htzFMNAME = dataSnapshot.child("HTZFM").child("Name").getValue().toString();
                        htzFMLINK = dataSnapshot.child("HTZFM").child("Link").getValue().toString();
                        htzFMDESCRIPTION = dataSnapshot.child("HTZFM").child("Description").getValue().toString();
                        htzFMURL = dataSnapshot.child("HTZFM").child("URL").getValue().toString();


                        indieFMNAME = dataSnapshot.child("INDIEFM").child("Name").getValue().toString();
                        indieFMLINK = dataSnapshot.child("INDIEFM").child("Link").getValue().toString();
                        indieFMDESCRIPTION = dataSnapshot.child("INDIEFM").child("Description").getValue().toString();
                        indieFMURL = dataSnapshot.child("INDIEFM").child("URL").getValue().toString();


                        jazzFMNAME = dataSnapshot.child("JAZZFM").child("Name").getValue().toString();
                        jazzFMLINK = dataSnapshot.child("JAZZFM").child("Link").getValue().toString();
                        jazzFMDESCRIPTION = dataSnapshot.child("JAZZFM").child("Description").getValue().toString();
                        jazzFMURL = dataSnapshot.child("JAZZFM").child("URL").getValue().toString();


                        kissFMNAME = dataSnapshot.child("KISSFM").child("Name").getValue().toString();
                        kissFMLINK = dataSnapshot.child("KISSFM").child("Link").getValue().toString();
                        kissFMDESCRIPTION = dataSnapshot.child("KISSFM").child("Description").getValue().toString();
                        kissFMURL = dataSnapshot.child("KISSFM").child("URL").getValue().toString();


                        newsTalkNAME = dataSnapshot.child("NEWSTALKRADIO").child("Name").getValue().toString();
                        newsTalkLINK = dataSnapshot.child("NEWSTALKRADIO").child("Link").getValue().toString();
                        newsTalkDESCRIPTION = dataSnapshot.child("NEWSTALKRADIO").child("Description").getValue().toString();
                        newsTalkURL = dataSnapshot.child("NEWSTALKRADIO").child("URL").getValue().toString();


                        q107NAME = dataSnapshot.child("Q107").child("Name").getValue().toString();
                        q107LINK = dataSnapshot.child("Q107").child("Link").getValue().toString();
                        q107DESCRIPTION = dataSnapshot.child("Q107").child("Description").getValue().toString();
                        q107URL = dataSnapshot.child("Q107").child("URL").getValue().toString();


                        virginRadioNAME = dataSnapshot.child("VirginRadio").child("Name").getValue().toString();
                        virginRadioLINK = dataSnapshot.child("VirginRadio").child("Link").getValue().toString();
                        virginRadioDESCRIPTION = dataSnapshot.child("VirginRadio").child("Description").getValue().toString();
                        virginRadioURL = dataSnapshot.child("VirginRadio").child("URL").getValue().toString();


                        z103NAME = dataSnapshot.child("Z103").child("Name").getValue().toString();
                        z103LINK = dataSnapshot.child("Z103").child("Link").getValue().toString();
                        z103DESCRIPTION = dataSnapshot.child("Z103").child("Description").getValue().toString();
                        z103URL = dataSnapshot.child("Z103").child("URL").getValue().toString();


                        theEdgeNAME = dataSnapshot.child("theEdge").child("Name").getValue().toString();
                        theEdgeLINK = dataSnapshot.child("theEdge").child("Link").getValue().toString();
                        theEdgeDESCRIPTION = dataSnapshot.child("theEdge").child("Description").getValue().toString();
                        theEdgeURL = dataSnapshot.child("theEdge").child("URL").getValue().toString();

                    //This is usually outside the ValueEventListener
                    setStations();
                    stationListAdapter = new StationListAdapter(activity, stations);
                    stationListView.setAdapter(stationListAdapter);
                    stationListView.setOnItemClickListener(StationListFragment.this);
                    stationListView.setOnItemLongClickListener(StationListFragment.this);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        return view;
    }

    private void setStations() {
        //Read in all our strings into an array list of type station
        Station theEdge = new Station(1, theEdgeNAME, theEdgeLINK,theEdgeDESCRIPTION,theEdgeURL);
        Station virginRadio  = new Station(2, virginRadioNAME, virginRadioLINK,virginRadioDESCRIPTION,virginRadioURL);
        Station q107 = new Station(3, q107NAME, q107LINK,q107DESCRIPTION,q107URL);
        Station z103 = new Station(4, z103NAME, z103LINK,z103DESCRIPTION,z103URL);
        Station classicalFM = new Station(5, classicalFMNAME, classicalFMLINK,classicalFMDESCRIPTION,classicalFMURL);
        Station htzFM = new Station(6, htzFMNAME, htzFMLINK, htzFMDESCRIPTION, htzFMURL);
        Station chumFM = new Station(7, chumFMNAME, chumFMLINK,chumFMDESCRIPTION,chumFMURL);
        Station choqFM = new Station(8, choqFMNAME, choqFMLINK,choqFMDESCRIPTION,choqFMURL);
        Station cjrtFM = new Station(9, jazzFMNAME, jazzFMLINK,jazzFMDESCRIPTION,jazzFMURL);
        Station chbmFM = new Station(10, boomFMNAME, boomFMLINK,boomFMDESCRIPTION,boomFMURL);
        Station cindFM = new Station(11, indieFMNAME, indieFMLINK,indieFMDESCRIPTION,indieFMURL);
        Station ckisFM = new Station(12, kissFMNAME, kissFMLINK,kissFMDESCRIPTION,kissFMURL);
        Station cfrb = new Station(13, newsTalkNAME, newsTalkLINK,newsTalkDESCRIPTION,newsTalkURL);
        Station cftr = new Station(14, news_680NAME, news_680LINK, news_680DESCRIPTION, news_680URL);
        Station chfiFM = new Station(15, chfiNAME, chfiLINK,chfiDESCRIPTION,chfiURL);

        stations = new ArrayList<Station>();

        stations.add(theEdge);
        stations.add(virginRadio);
        stations.add(z103);
        stations.add(q107);
        stations.add(classicalFM);
        stations.add(htzFM);
        stations.add(chumFM);
        stations.add(choqFM);
        stations.add(cjrtFM);
        stations.add(chbmFM);
        stations.add(cindFM);
        stations.add(ckisFM);
        stations.add(cfrb);
        stations.add(cftr);
        stations.add(chfiFM);
    }

    private void findViewsById(View view) { stationListView = (ListView) view.findViewById(R.id.list_station);}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Station station = (Station) parent.getItemAtPosition(position);

        String description = station.getDescription();
        String streamLink = station.getLink();
        String imageURL = station.getImageURL();

        i = new Intent(getActivity(), RadioActivity.class);

        i.putExtra(getActivity().getString(R.string.description), description);
        i.putExtra(getActivity().getString(R.string.link), streamLink);
        i.putExtra(getActivity().getString(R.string.imageurl), imageURL);

        startActivity(i);
        /*Use station.postion to decide which stream is being selected then run Radio activity*/
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long arg3) {
    //Handles all the favouriting tasks .  This function is repated in FavouriteListFragment
        ImageView button = (ImageView) view.findViewById(R.id.imgbtn_favorite);
        String tag = button.getTag().toString();
        if (tag.equalsIgnoreCase(getString(R.string.grey))) {
            sharedPreference.addFavorite(activity, stations.get(position));
            Toast.makeText(activity, activity.getResources().getString(R.string.add_favr), Toast.LENGTH_SHORT).show();
            button.setTag(getString(R.string.red));
            button.setImageResource(R.drawable.heart_red);
        } else {
            sharedPreference.removeFavorite(activity, stations.get(position));
            button.setTag(getString(R.string.grey));
            button.setImageResource(R.drawable.heart_grey);
            Toast.makeText(activity, activity.getResources().getString(R.string.remove_favr), Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
