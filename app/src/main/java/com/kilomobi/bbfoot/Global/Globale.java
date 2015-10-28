package com.kilomobi.bbfoot.Global;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by Nawako on 24/10/2015.
 */
public class Globale {
    public static String webURL ="http://bbfoot.azurewebsites.net/api/";
    public static String PLAYER = "player/";
    public static String MATCH = "match/";
    public static String DELETE = "suppression";
    public static String CREATE = "creation";
    public static String MODIFY = "modification";

    public static byte[] getBlob (Bitmap bitmap) {
        if (bitmap!=null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            return stream.toByteArray();
        }
        return null;
    }

}
