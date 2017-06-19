package com.example.pzend.swim_lab5;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by pzend on 19.06.2017.
 */

public class PlayerActivity extends AppCompatActivity {
    int chosenSongPosition;
    Song chosenSong;
    MediaPlayer mediaPlayer;
    ImageButton pauseButton;
    SeekBar seekBar;
    boolean isPlaying;
    private Handler handler = new Handler();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chosenSongPosition = getIntent().getIntExtra("position", 0);
        chosenSong = MainActivity.songs.get(chosenSongPosition);
        setContentView(R.layout.activity_player);
        ImageView cover = (ImageView)findViewById(R.id.player_cover);
        cover.setImageResource(chosenSong.getCoverID());
        TextView title = (TextView)findViewById(R.id.player_title);
        title.setText(chosenSong.getTitle());
        TextView artist = (TextView)findViewById(R.id.player_artist);
        artist.setText(chosenSong.getArtist());
        pauseButton = (ImageButton)findViewById(R.id.play_button);
        isPlaying = true;
        pauseButton.setOnClickListener(pauseButtonListener);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), chosenSong.getSoundResID());
        mediaPlayer.start();

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBar.setMax(mediaPlayer.getDuration());

        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(currentPosition);
                }
                handler.postDelayed(this, 500);
            }
        });
    }


    private View.OnClickListener pauseButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pauseButton.setImageResource(isPlaying ? android.R.drawable.ic_media_play : android.R.drawable.ic_media_pause);
            if (isPlaying) {
                mediaPlayer.pause();
            }
            else mediaPlayer.start();
            isPlaying = !isPlaying;
        }
    };

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //Toast.makeText(getApplicationContext(), ""+progress, Toast.LENGTH_SHORT).show();
            //double secondsInSong = ;
            //double secondsToGo = (progress/100.0)*(mediaPlayer.getDuration());
            if (fromUser) mediaPlayer.seekTo(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }
}
