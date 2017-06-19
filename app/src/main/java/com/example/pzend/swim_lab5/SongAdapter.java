package com.example.pzend.swim_lab5;

import android.app.Activity;
import android.content.Context;
import android.test.suitebuilder.TestMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pzend on 19.06.2017.
 */

public class SongAdapter extends BaseAdapter {
    private Activity activity;
    private static LayoutInflater inflater = null;
    private ArrayList data;
    Song tempVals = null;
    public SongAdapter(Activity act, ArrayList d){
        activity = act;
        data = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static class SongViewHolder{
        public TextView title;
        public TextView artist;
        public ImageView cover;
    }


    @Override
    public int getCount() {
        return data.size()<=0 ? 1 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        SongViewHolder holder;
        if(convertView == null){
            view = inflater.inflate(R.layout.song_row, null);
            holder = new SongViewHolder();
            holder.artist = (TextView) view.findViewById(R.id.list_artist);
            holder.title = (TextView) view.findViewById(R.id.list_title);
            holder.cover = (ImageView) view.findViewById(R.id.list_cover);
            view.setTag(holder);
        }
        else holder = (SongViewHolder)view.getTag();

        if(data.size() <= 0)
            holder.title.setText("Nothing here!");
        else {
            tempVals = null;
            tempVals = (Song) data.get(position);
            holder.title.setText(tempVals.getTitle());
            holder.artist.setText(tempVals.getArtist());
            holder.cover.setImageResource(tempVals.getCoverID());
        }
        return view;
    }
}
