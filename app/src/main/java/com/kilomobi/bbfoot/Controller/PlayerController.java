package com.kilomobi.bbfoot.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kilomobi.bbfoot.Model.Player;
import com.kilomobi.bbfoot.R;

import java.util.ArrayList;


public class PlayerController extends ArrayAdapter<Player> {

    // Save the clicked favorite player
    private int mFavoriteID = -1;
    // Get the last row item to change color
    private RelativeLayout lastRelativeLayout;
    private TextView lastTextView;

    public PlayerController(Context context, ArrayList<Player> players) {
        super(context, 0, players);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item
        Player player = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.activity_player_chooser_row, parent, false);
        }
        // Get the relativeLayout to change his bakcground color when clicked
        final RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.activity_player_chooser_row_rl);
        // Populate the data into the template view using the data object
        TextView tvName = (TextView) view.findViewById(R.id.activity_player_chooser_row_player_name);
        //	TextView tvEmail = (TextView) view.findViewById(R.id.player_email);
        final TextView tvPrenom = (TextView) view.findViewById(R.id.activity_player_chooser_row_player_firstname);

        if (position != mFavoriteID) {
            // change color of latest row
            relativeLayout.setBackgroundResource(R.color.dim_foreground_material_dark);
            tvPrenom.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
            // set defaults colors
            relativeLayout.setBackgroundResource(R.color.dim_foreground_material_dark);
            tvPrenom.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
        } else {
            // set favorites colors
            relativeLayout.setBackgroundResource(R.color.colorAccent);
            tvPrenom.setTextColor(getContext().getResources().getColor(R.color.background_material_light));
        }

        tvName.setText(player.getNom());
        tvPrenom.setText(player.getPrenom());
        
        // Click listener
        View.OnClickListener clickedPlayer = new View.OnClickListener() {
            public void onClick(View v) {

                if (mFavoriteID != position) {
                    try {
                        lastRelativeLayout.setBackgroundResource(R.color.dim_foreground_material_dark);
                        lastTextView.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
                    } catch (NullPointerException e) { }
                }
                // set the row as the latest
                lastRelativeLayout = relativeLayout;
                lastTextView = tvPrenom;

                // change color of actual row
                relativeLayout.setBackgroundResource(R.color.colorAccent);
                tvPrenom.setTextColor(getContext().getResources().getColor(R.color.background_material_light));
                mFavoriteID = position;
            }
        };

        // Assigne le click au click listener
        view.setOnClickListener(clickedPlayer);

        return view;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    @Override
    public boolean isEnabled(int arg0)
    {
        return true;
    }
}