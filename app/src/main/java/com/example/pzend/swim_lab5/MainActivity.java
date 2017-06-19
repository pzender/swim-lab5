package com.example.pzend.swim_lab5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Song> songs = new ArrayList<>();
    ListView songListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepSongs();
        songListView = (ListView) findViewById(R.id.song_list);
        songListView.setAdapter(new SongAdapter(this, songs));
        songListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), songs.get(i).getTitle() + " selected!", Toast.LENGTH_SHORT).show();
                Intent playIntent = new Intent(getApplicationContext(), PlayerActivity.class);
                playIntent.putExtra("position", i);
                startActivity(playIntent);
            }
        });
    }

    private void prepSongs(){
        Song s = new Song("Seven Headed Whore", "Iced Earth", R.drawable.iced_earth, R.raw.seven_headed);
        songs.add(s);

        s = new Song("Isara", "Eluveitie", R.drawable.eluveitie, R.raw.isara);
        songs.add(s);

        s = new Song("In the Groves of Death", "Insomnium", R.drawable.insomnium, R.raw.groves);
        songs.add(s);
    }
}

