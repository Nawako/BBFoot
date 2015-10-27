package com.kilomobi.bbfoot;

import java.io.File;

import android.os.Environment;

public final class CustomAlbum {

    public File getAlbumStorageDir(String albumName) {
        return new File (
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                albumName
        );
    }
}
