package com.kilomobi.bbfoot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.dd.morphingbutton.MorphingButton;
import com.kilomobi.bbfoot.Async.PlayerAddAsync;
import com.kilomobi.bbfoot.Controller.PlayerController;
import com.kilomobi.bbfoot.Model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nawako on 22/10/2015.
 */
public class PlayerAddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNom;
    EditText etPrenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_add);

        etNom = (EditText) findViewById(R.id.activity_player_add_et_prenom);
        etPrenom = (EditText) findViewById(R.id.activity_player_add_et_nom);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_player_add_btn_valider:
                if(etNom.getText().toString().isEmpty())
                    etNom.setError("S'il vous plaît entrez un nom");
                if(etPrenom.getText().toString().isEmpty())
                    etPrenom.setError("S'il vous plaît entrez un prenom");
                break;
        }
    }

    void validate() {
        // Async
        PlayerAddAsync asyncAdd = new PlayerAddAsync(this, this);
        // Params pour add un joueur
        asyncAdd.execute("", etNom.getText().toString(), etPrenom.getText().toString());
    }
}
