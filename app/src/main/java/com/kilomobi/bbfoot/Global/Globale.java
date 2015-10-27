package com.kilomobi.bbfoot.Global;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.kilomobi.bbfoot.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.sql.Blob;

/**
 * Created by Nawako on 24/10/2015.
 */
public class Globale {
    public static String webURL ="http://bbfoot.azurewebsites.net/api/";
    public static String GET = "player";
    public static String DELETE = "player/suppression";
    public static String CREATE = "player/creation";
    public static String MODIFY = "player/modification";

    public static byte[] getBlob (Bitmap bitmap) {
        if (bitmap!=null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            return stream.toByteArray();
        }
        return null;
    }

}
