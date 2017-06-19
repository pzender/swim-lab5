package com.example.pzend.swim_lab5;

/**
 * Created by pzend on 19.06.2017.
 */

public class Song {
    Song(String title, String artist, int coverID, int soundResID){
        this.title = title;
        this.artist = artist;
        this.coverID = coverID;
        this.soundResID = soundResID;
    }

    private String title;
    private String artist;
    private int coverID;
    private int soundResID;

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getCoverID() {
        return coverID;
    }

    public int getSoundResID() {
        return soundResID;
    }
}
