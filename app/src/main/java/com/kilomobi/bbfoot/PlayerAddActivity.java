package com.kilomobi.bbfoot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kilomobi.bbfoot.Async.PlayerAddAsync;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nawako on 22/10/2015.
 */
public class PlayerAddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNom;
    EditText etPrenom;
    CircleImageView ciImage;
    Button btnValider;
    private CustomAlbum mAlbumStorageDirFactory = null;
    static final int REQUEST_TAKE_PHOTO = 1;
    String mCurrentPhotoPath;
    private static final String JPEG_FILE_PREFIX = "PLAYER_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    String imageFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_add);

        etNom = (EditText) findViewById(R.id.activity_player_add_et_prenom);
        etPrenom = (EditText) findViewById(R.id.activity_player_add_et_nom);
        ciImage = (CircleImageView) findViewById(R.id.activity_player_add_ci);
        btnValider = (Button) findViewById(R.id.activity_player_add_btn_valider);

        etNom.setOnClickListener(this);
        etPrenom.setOnClickListener(this);
        ciImage.setOnClickListener(this);
        btnValider.setOnClickListener(this);

        mAlbumStorageDirFactory = new CustomAlbum();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_player_add_btn_valider:
                etNom.setError(null);
                etPrenom.setError(null);
                if(etNom.getText().toString().isEmpty()) {
                    etNom.setError("S'il vous plaît entrez un nom");
                    break;
                }

                if(etPrenom.getText().toString().isEmpty()) {
                    etPrenom.setError("S'il vous plaît entrez un prenom");
                    break;
                }
                validate();
                break;
            case R.id.activity_player_add_ci:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    dispatchTakePictureIntent(REQUEST_TAKE_PHOTO);
                }
        }
    }

    void validate() {
        // Async
        PlayerAddAsync asyncAdd = new PlayerAddAsync(this, this);
        // Params pour add un joueur
        asyncAdd.execute("", etNom.getText().toString(), etPrenom.getText().toString(), imageFileName);
    }

    // Créer un nom de fichier
    private File createImageFile() throws IOException {
        long time = System.currentTimeMillis();
        imageFileName = JPEG_FILE_PREFIX + Long.toString(time) + "";
        File albumF = mAlbumStorageDirFactory.getAlbumDir();
        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
        String path = imageF.getPath();
        imageFileName = path.substring(path.lastIndexOf("/") + 1);
        return imageF;
    }

    private void dispatchTakePictureIntent(int actionCode) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        switch(actionCode) {
            case REQUEST_TAKE_PHOTO:
                File f = null;

                try {
                    f = setUpPhotoFile();
                    mCurrentPhotoPath = f.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                } catch (IOException e) {
                    e.printStackTrace();
                    f = null;
                    mCurrentPhotoPath = null;
                }
                break;

            default:
                break;
        }
        startActivityForResult(takePictureIntent, actionCode);
    }

    // Créer l'image et y associe son chemin
    private File setUpPhotoFile() throws IOException {
        File f = createImageFile();
        mCurrentPhotoPath = f.getAbsolutePath();

        return f;
    }

    // Ajoute la photo à la gallerie de l'appareil
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            handleBigCameraPhoto();
        }
    }

    // Gestion de l'image prise par l'appareil
    private void handleBigCameraPhoto() {

        if (mCurrentPhotoPath != null) {
            setPic();
            galleryAddPic();
        }
    }

    private void setPic() {
        // Il n'y a pas assez de mémoire pour ouvrir plus de quelques photos
        // Alors il faut pré-définir la taille de la photo qui sera décodé

		// Récupère la taille du CircleImageView
        int targetW = ciImage.getWidth();
        int targetH = ciImage.getHeight();

		// Récupère la taille de l'image
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

		// Réduction de l'image
        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        }

		// Modifie les paramètres Bitmap pour coller à la destination
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

		// Décode le fichier JPEG en fichier Bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

		// Associe le CircleImageView au bitmap
        ciImage.setImageBitmap(bitmap);
    }
}