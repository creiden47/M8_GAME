package com.example.franciscogarzil.m8_game;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {

    public MediaPlayer mMediaPlayer;

    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void play(Context c, int rid) {
        mMediaPlayer = MediaPlayer.create(c, rid);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stop();
            }

        });
        mMediaPlayer.start();
    }

}