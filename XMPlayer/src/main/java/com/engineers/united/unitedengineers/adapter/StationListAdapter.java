/*
UNITED ENGINEERS

*/
package com.engineers.united.unitedengineers.adapter;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.engineers.united.unitedengineers.R;
import com.engineers.united.unitedengineers.beans.Station;
import com.engineers.united.unitedengineers.utils.SharedPreference;

/**
 * Created by darren on 2017-11-18.
 */

public class StationListAdapter extends ArrayAdapter<Station> {

    private Context context;
    List<Station> stations;
    SharedPreference sharedPreference;

    public StationListAdapter(Context context, List<Station> stations) {
        super(context, R.layout.station_list_item, stations);
        this.context = context;
        this.stations = stations;
        sharedPreference = new SharedPreference();
    }

    private class ViewHolder {
        TextView stationNameTxt;
        ImageView favoriteImg;
    }

    @Override
    public int getCount() {
        return stations.size();
    }

    @Override
    public Station getItem(int position) {
        return stations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.station_list_item, null);
            holder = new ViewHolder();
            holder.stationNameTxt = (TextView) convertView.findViewById(R.id.txt_sta_name);

            holder.favoriteImg = (ImageView) convertView.findViewById(R.id.imgbtn_favorite);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Station station = (Station) getItem(position);
        holder.stationNameTxt.setText(station.getName());

		/*If a station exists in shared preferences then set heart_red drawable
		 * and set a tag*/
        if (checkFavoriteItem(station)) {
            holder.favoriteImg.setImageResource(R.drawable.heart_red);
            holder.favoriteImg.setTag(getContext().getString(R.string.red));
        } else {
            holder.favoriteImg.setImageResource(R.drawable.heart_grey);
            holder.favoriteImg.setTag(getContext().getString(R.string.grey));
        }

        return convertView;
    }

    /*Checks whether a particular station exists in SharedPreferences*/
    public boolean checkFavoriteItem(Station checkStation) {
        boolean check = false;
        List<Station> favorites = sharedPreference.getFavorites(context);
        if (favorites != null) {
            for (Station station : favorites) {
                if (station.equals(checkStation)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @Override
    public void add(Station station) {
        super.add(station);
        stations.add(station);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Station station) {
        super.remove(station);
        stations.remove(station);
        notifyDataSetChanged();
    }
}