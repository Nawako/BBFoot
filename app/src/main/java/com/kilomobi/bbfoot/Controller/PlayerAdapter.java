package com.kilomobi.bbfoot.Controller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kilomobi.bbfoot.CustomAlbum;
import com.kilomobi.bbfoot.Model.Player;
import com.kilomobi.bbfoot.R;
import com.kilomobi.bbfoot.Singleton;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayerAdapter extends ArrayAdapter<Player> {

    private final List<Player> list;
    private List<Player> listOfRedPlayers;
    private List<Player> listOfBluePlayers;
    private final Activity context;
    boolean checkAll_flag = false;
    boolean checkItem_flag = false;
    int numberOfSelectedRedPlayer;
    int numberOfSelectedBluePlayer;
    private CustomAlbum mAlbumStorageDirFactory = null;

    public PlayerAdapter(Activity context, ArrayList<Player> list) {
        super(context, R.layout.activity_player_chooser_row, list);
        this.context = context;
        this.list = list;
        mAlbumStorageDirFactory = new CustomAlbum();
        numberOfSelectedRedPlayer = 0;
        numberOfSelectedBluePlayer = 0;
    }

    static class ViewHolder {
        protected RelativeLayout rl;
        protected int id;
        protected TextView nom;
        protected TextView prenom;
        protected String imagePath;
        protected CircleImageView image;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        final RelativeLayout rl = viewHolder.rl;

                if (p.isSelected())
                    viewHolder.rl.setBackgroundResource(R.color.mb_gray);
                else
                    viewHolder.rl.setBackgroundResource(R.color.colorPrimary);

                viewHolder.rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (Singleton.getInstance().getState()) {
                            case 1:
                                if (p.isSelected()) {
                                    rl.setBackgroundResource(R.color.colorPrimary);
                                    p.setSelected(false);
                                    numberOfSelectedRedPlayer--;
                                } else {
                                    if (numberOfSelectedRedPlayer < 2 && !isIdPlayerAlreadySelected(position)) {
                                        rl.setBackgroundResource(R.color.mb_gray);
                                        p.setSelected(true);
                                        numberOfSelectedRedPlayer++;
                                    } else {
                                        Toast.makeText(getContext(),
                                                "Vous ne pouvez pas être plus de 2 d'un côté // Sélectionner un joueur déjà sélectionner !",
                                                Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }
                                break;
                            case 2:
                                if (p.isSelected() && !getListOfRedPlayers().contains(p)) {
                                    rl.setBackgroundResource(R.color.colorAccent);
                                    p.setSelected(false);
                                    numberOfSelectedBluePlayer--;
                                } else {
                                    if (numberOfSelectedBluePlayer < 2 && !isIdPlayerAlreadySelected(position) && !getListOfRedPlayers().contains(p)) {
                                        rl.setBackgroundResource(R.color.mb_gray);
                                        p.setSelected(true);
                                        numberOfSelectedBluePlayer++;
                                    } else {
                                        Toast.makeText(getContext(),
                                                "Vous ne pouvez pas être plus de 2 d'un côté // Sélectionner un joueur déjà sélectionner !",
                                                Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }
                                break;
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

    public List<Player> getList() {
        return list;
    }

    public List<Integer> getListOfSelectedPlayers() {
        List<Integer> listIds = new ArrayList<>();
        for (int i = 0; i< list.size(); i++) {
            if (list.get(i).isSelected())
                listIds.add(list.get(i).get_id());
        }
        return listIds;
    }

    public List<Player> getListOfSelectedPlayersAsPlayer() {
        List<Player> listPlayers = new ArrayList<>();
        for (int i = 0; i< list.size(); i++) {
            if (list.get(i).isSelected())
                listPlayers.add(list.get(i));
        }
        return listPlayers;
    }

    public boolean isIdPlayerAlreadySelected(int playerId) {
        return list.get(playerId).isSelected();
    }

    public void setSelectionAtFalse() {
        for (int i = 0; i< list.size(); i++) {
            list.get(i).setSelected(false);
        }
    }

    public List<Player> getListOfRedPlayers() {
        return listOfRedPlayers;
    }

    public void setListOfRedPlayers(List<Player> listOfRedPlayers) {
        this.listOfRedPlayers = listOfRedPlayers;
    }

    public List<Player> getListOfBluePlayers() {
        return listOfBluePlayers;
    }

    public void setListOfBluePlayers(List<Player> listOfBluePlayers) {
        this.listOfBluePlayers = listOfBluePlayers;
    }
}
