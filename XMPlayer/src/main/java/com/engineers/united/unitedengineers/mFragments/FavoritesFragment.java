/*
UNITED ENGINEERS
Future functionality in this class to be added
- Retrieve and display all radio stations that have been favourited in the previous
- Tab fragment
 */
package com.engineers.united.unitedengineers.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.engineers.united.unitedengineers.R;
/**
 * Created by Oclemmy on 5/10/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class FavoritesFragment extends Fragment {
    String[] favorite={"Favorites"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.favorites_fragment,container,false);
        ListView lv= (ListView) rootView.findViewById(R.id.favoriteListView);
        ArrayAdapter adapter=new ArrayAdapter(this.getActivity(),android.R.layout.simple_list_item_1,favorite);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), favorite[position], Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}