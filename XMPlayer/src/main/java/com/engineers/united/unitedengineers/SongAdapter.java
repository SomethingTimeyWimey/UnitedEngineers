/*
    United Engineers
    this activity serves as a bridge between the user interface and the actual song data
 */

package com.engineers.united.unitedengineers;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {

    List<Song> songList;

    MusicPlayerActivity mainActivity;


    public SongAdapter(MusicPlayerActivity mainActivity, List<Song> songList) {
        this.songList=songList;
        this.mainActivity=mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_song_view,
                parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Song song=songList.get(position);
        holder.songTitle.setText(song.getTitle());
        if(song.isPlaying())
        {
            holder.songTitle.setTextColor(Color.BLUE);
            holder.songTitle.setTypeface(null, Typeface.BOLD);

        }else
        {
            holder.songTitle.setTextColor(Color.BLACK);
            holder.songTitle.setTypeface(null, Typeface.NORMAL);
        }
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView songTitle;

        public MyViewHolder(View itemView) {
            super(itemView);

            songTitle=(TextView)itemView.findViewById(R.id.songTitle);
        }
    }
}