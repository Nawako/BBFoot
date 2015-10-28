package com.kilomobi.bbfoot.Controller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kilomobi.bbfoot.CustomAlbum;
import com.kilomobi.bbfoot.Model.Player;
import com.kilomobi.bbfoot.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayerAdapter extends ArrayAdapter<Player> {

    private final List<Player> list;
    private final Activity context;
    boolean checkAll_flag = false;
    boolean checkItem_flag = false;
    private CustomAlbum mAlbumStorageDirFactory = null;

    public PlayerAdapter(Activity context, ArrayList<Player> list) {
        super(context, R.layout.activity_player_chooser_row, list);
        this.context = context;
        this.list = list;
        mAlbumStorageDirFactory = new CustomAlbum();
    }

    static class ViewHolder {
        protected TextView nom;
        protected TextView prenom;
        protected String imagePath;
        protected CircleImageView image;
        protected CheckBox checkbox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.activity_player_chooser_row, null);
            viewHolder = new ViewHolder();
            viewHolder.prenom = (TextView) convertView.findViewById(R.id.activity_player_chooser_row_tv_player_firstname);
            viewHolder.nom = (TextView) convertView.findViewById(R.id.activity_player_chooser_row_tv_player_name);
            viewHolder.image = (CircleImageView) convertView.findViewById(R.id.activity_player_chooser_row_ci_image);
            viewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.activity_player_chooser_row_cb);
            viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();  // Ici on récupère la position que l'on a mise avec la checkbox utilisant setTag
                    list.get(getPosition).setIsSelected(buttonView.isChecked()); // Récupère la valeur du checkbox pour qu'il ne parte pas en cahuète.
                }
            });
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.activity_player_chooser_row_tv_player_firstname, viewHolder.prenom);
            convertView.setTag(R.id.activity_player_chooser_row_tv_player_name, viewHolder.nom);
            convertView.setTag(R.id.activity_player_chooser_row_ci_image, viewHolder.image);
            convertView.setTag(R.id.activity_player_chooser_row_cb, viewHolder.checkbox);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Important
        viewHolder.checkbox.setTag(position);

        viewHolder.prenom.setText(list.get(position).getPrenom());
        viewHolder.nom.setText(list.get(position).getPrenom());

        viewHolder.imagePath = mAlbumStorageDirFactory.getAlbumStorageDir("BBFoot") + "/" + list.get(position).getImage();

        Picasso.with(getContext())
                .load(new File(viewHolder.imagePath))
                .placeholder(R.drawable.default_avatar)
                .resize(200, 200)
                .centerCrop()
                .into(viewHolder.image);

        viewHolder.checkbox.setChecked(list.get(position).isSelected());

        return convertView;
    }
}