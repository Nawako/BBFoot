package com.kilomobi.bbfoot;

import java.io.File;

import android.os.Environment;
import android.util.Log;

public final class CustomAlbum {

    public File getAlbumStorageDir(String albumName) {
        return new File (
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                albumName
        );
    }

    // Album photo spécifique à l'application
    private String getAlbumName() {
        return "BBFoot";
    }

    // Récupère le répertoire où se loge les images
    public File getAlbumDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            storageDir = getAlbumStorageDir(getAlbumName());

            if (storageDir != null) {
                if (! storageDir.mkdirs()) {
                    if (! storageDir.exists()){
                        Log.d("getAlbumDir", "Echec création répertoire");
                        return null;
                    }
                }
            }

        } else {
            Log.v("getAlbumDir", "Stockage externe n'est pas en WRITE/READ");
        }
        return storageDir;
    }
}
