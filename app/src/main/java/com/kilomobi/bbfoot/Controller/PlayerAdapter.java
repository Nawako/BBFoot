package com.kilomobi.bbfoot.Controller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
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
        protected RelativeLayout rl;
        protected TextView nom;
        protected TextView prenom;
        protected String imagePath;
        protected CircleImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Player p = getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.activity_player_chooser_row, null);
        }
        viewHolder = new ViewHolder();
        viewHolder.prenom = (TextView) convertView.findViewById(R.id.activity_player_chooser_row_tv_player_firstname);
        viewHolder.nom = (TextView) convertView.findViewById(R.id.activity_player_chooser_row_tv_player_name);
        viewHolder.image = (CircleImageView) convertView.findViewById(R.id.activity_player_chooser_row_ci_image);
        viewHolder.rl = (RelativeLayout) convertView.findViewById(R.id.activity_player_chooser_row_rl);

        if (p.isSelected())
            viewHolder.rl.setBackgroundResource(R.color.colorAccent);
        else
            viewHolder.rl.setBackgroundResource(R.color.colorPrimary);

        final RelativeLayout rl = viewHolder.rl;

        viewHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (p.isSelected()) {
                    rl.setBackgroundResource(R.color.colorPrimary);
                    p.setIsSelected(false);
                } else {
                    rl.setBackgroundResource(R.color.colorAccent);
                    p.setIsSelected(true);
                }
            }
        });

        viewHolder.prenom.setText(list.get(position).getPrenom());
        viewHolder.nom.setText(list.get(position).getNom());

        viewHolder.imagePath = mAlbumStorageDirFactory.getAlbumStorageDir("BBFoot") + "/" + list.get(position).getImage();

        Picasso.with(getContext())
                .load(new File(viewHolder.imagePath))
                .placeholder(R.drawable.default_avatar)
                .resize(200, 200)
                .centerCrop()
                .into(viewHolder.image);

        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Player getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(Player item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public int getAllSelected() {

        return 1;
    }
}
