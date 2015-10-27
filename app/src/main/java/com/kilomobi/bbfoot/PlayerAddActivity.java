package com.kilomobi.bbfoot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.kilomobi.bbfoot.Async.PlayerAddAsync;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nawako on 22/10/2015.
 */
public class PlayerAddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNom;
    EditText etPrenom;
    CircleImageView ciImage;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_add);

        etNom = (EditText) findViewById(R.id.activity_player_add_et_prenom);
        etPrenom = (EditText) findViewById(R.id.activity_player_add_et_nom);
        ciImage = (CircleImageView) findViewById(R.id.activity_player_add_ci);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_player_add_btn_valider:
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
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
        }
    }

    void validate() {
        // Async
        PlayerAddAsync asyncAdd = new PlayerAddAsync(this, this);
        // Params pour add un joueur
        asyncAdd.execute("", etNom.getText().toString(), etPrenom.getText().toString());
    }

    private File createImageFile() throws IOException {
        // Créer un nom d'image
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(0));
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Sauvegarde l'image dans la gallerie par défaut
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Vérifie qu'une camera activity prendre l'intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Créer le fichier là où il doit se trouver (DCIM)
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error en créant le fichier

            }
            // Continue seulement si le fichier a été créé
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    // Ajoute la photo à la gallerie de l'appareil
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ciImage.setImageBitmap(imageBitmap);
        }
    }
}
