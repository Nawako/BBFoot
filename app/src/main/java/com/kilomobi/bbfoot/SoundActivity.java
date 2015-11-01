package com.kilomobi.bbfoot;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaActionSound;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by macbookpro on 30/10/2015.
 */
public class SoundActivity extends MediaPlayer {

    MediaPlayer player;

    public void openSound(Context ctx, String mp3) {
        player = this;
        try {
            AssetFileDescriptor afd = ctx.getAssets().openFd(mp3);
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.prepare();
            player.start();
            player.setVolume(100f, 100f);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void stopPlaying() {
        if (player != null) {
            player.stop();
            player.reset();
            player = null;
        }
    }
}
