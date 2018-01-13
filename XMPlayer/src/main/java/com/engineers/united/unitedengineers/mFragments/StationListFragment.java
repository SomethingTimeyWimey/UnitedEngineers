/*
UNITED ENGINEERS
Was use to add products to the list and populate them with meaningful data
- handles data for the list item
- displaying toast alerts
*/
package com.engineers.united.unitedengineers.mFragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import static android.content.ContentValues.TAG;

/**
 * Created by darren on 2017-11-18.
 */

public class StationListFragment extends Fragment implements OnItemClickListener, OnItemLongClickListener {

    public static final String ARG_ITEM_ID = "station_list";

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

    //Ill link you guys to the database but it might only be
    // available to me right now so I gotta figure out how to let you guys see it to.
    // ("https://console.firebase.google.com/project/unitedengineers-49749/database/unitedengineers-49749/data")

    //I think we should put our logic in the onCreate or onCreateView so our
    //radio list can pull in stations when the app is powered on
    //As well the listener only retreives data if theres been a change so it wont
    // load all the data every single time the app gets executed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        sharedPreference = new SharedPreference();

        //Intializing those above references
        mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference("Station");


        //There has to be some sort of listener..this one waits for new data to be added
        //then adds it in real time to our app

        //this code below I got from the firebase website (https://firebase.google.com/docs/database/android/start/)
        //doesnt throw an error just does nothing as far as I can see

        /*reference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                //We have to use some sort of data snapshot to capture the data
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/

        //Below is the previous way ive tried to read in data
        //and without IF/ELSE statement
        //our app will crash with a null pointer exception
        //java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.Object java.util.HashMap.get(java.lang.Object)' on a null object reference
        //this popped up when I was trying the SQLite database as well
        //So Im probably missing something withing the code or within the database im not sure
        //Heres a link to the tutorial I got this code from ("https://www.youtube.com/watch?v=P9h6ukPQU_4")
        //Skip ahead to about 50:30 for the part im getting this from
    reference.addValueEventListener(new ValueEventListener() {

        //As you can see im just trying to read the parent field in our database as an object
        //then then run toString() and just print to the log what it returns.
        //But the app never gets that far
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            HashMap<String, Object> yourData = (HashMap<String, Object>) dataSnapshot.getValue();

           // if(yourData.get("Station")!=null){
            //hopefully .get will be highlighted yellow for you as well
            //and it does give the warning that it may return nullPointerException
            //but ive had this warning before and it hasnt got in my way
            //maybe a different method will be used, but ive looked at the page for HashMap and this seems like the only way
            //to read in data. heres the webpage ("https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html")
            //maybe values()? It returns a Collection of our values.
            //but I was hoping to read in the object from our database and immediately put it into our Station object
                Station station = (Station)yourData.get("Station");
                String string = station.toString();
                Log.v("Station", string );
            //} else { Log.getStackTraceString(null); }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    }

    //Again we could have the code implemented here but I dont think thats our issue
    //because ive copied pasted different code into this method and the same error occurs.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_station_list, container, false);
        findViewsById(view);
        stations = new ArrayList<Station>();

        setStations();
        stationListAdapter = new StationListAdapter(activity, stations);
        stationListView.setAdapter(stationListAdapter);
        stationListView.setOnItemClickListener(this);
        stationListView.setOnItemLongClickListener(this);
        return view;
    }

    private void setStations() {

        Station theEdge = new Station(1, "102.1 theEdge", "http://live.leanstream.co/CFNYFM?tunein",null,null);
        Station virginRadio  = new Station(2, "99.9 VirginRadio", "http://16143.live.streamtheworld.com/CKFMFMAAC_SC",null,null);
        Station q107 = new Station(3, "Q107 CILQ-FM", "http://live.leanstream.co/CILQFM-MP3?tunein",null,null);
        Station z103 = new Station(4, "Z103.5 CIDC-FM", "http://ice66.securenetsystems.net/CIDC2",null,null);
        Station classicalFM = new Station(5, "96.3 Classical-FM", "http://radiostream.zoomer.ca:8000/cfmo.mp3",null,null);
        Station htzFM = new Station(6, "97.7 HTZ-FM", "http://16803.live.streamtheworld.com/CHTZFMAAC.aac?",null,null);
        Station chumFM = new Station(7, "104.5 CHUM-FM", "http://16143.live.streamtheworld.com/CHUMFMAAC_SC",null,null);
        Station choqFM = new Station(8, "105.1 CHOQ-FM", "http://ice9.securenetsystems.net/CHOQ?&playSessionID=C4DEB4BF-FCBC-DE66-342C16E61E474857",null,null);
        Station cjrtFM = new Station(9, "91.1 JAZZ-FM", "http://ice66.securenetsystems.net/CJRT?&playSessionID=C5DC9A16-940D-CC1C-8317B99521F411EB",null,null);
        Station chbmFM = new Station(10, "97.3 BOOM", "http://ice66.securenetsystems.net/CJRT?&playSessionID=C5DC9A16-940D-CC1C-8317B99521F411EB",null,null);
        Station cindFM = new Station(11, "88.1 INDIE", "http://indie.streamon.fm:8000/indie-48k.aac",null,null);
        Station ckisFM = new Station(12, "92.5 KISS", "http://tor925.akacast.akamaistream.net/7/288/80873/v1/rogers.akacast.akamaistream.net/tor925",null,null);
        Station cfrb = new Station(13, "1010 NEWSTALK", "http://16843.live.streamtheworld.com/CFRBAMAAC_SC",null,null);
        Station cftr = new Station(14, "680 NEWS", "http://radio_cftr-lh.akamaihd.net/i/TOR680_1@176946/master.m3u8",null,null);
        Station chfiFM = new Station(15, "98.1 CHFI", "http://tor981.akacast.akamaistream.net/7/550/80872/v1/rogers.akacast.akamaistream.net/tor981",null,null);

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

        String name = station.getName();
        String streamLink = station.getLink();
        String description = station.getDescription();
        String imageURL = station.getImageURL();

        i = new Intent(getActivity(), RadioActivity.class);

        i.putExtra("name", name);
        i.putExtra("streamLink", streamLink);
        i.putExtra("description", description);
        i.putExtra("imageURL", imageURL);

        i.putExtra(getString(R.string.link), streamLink);

        startActivity(i);
        /*Use station.postion to decide which stream is being selected then run Radio activity*/
        //Toast.makeText(activity, station.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long arg3) {

        ImageView button = (ImageView) view.findViewById(R.id.imgbtn_favorite);
        String tag = button.getTag().toString();
        if (tag.equalsIgnoreCase(getString(R.string.grey))) {
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
