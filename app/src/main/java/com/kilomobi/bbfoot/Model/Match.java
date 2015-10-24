package com.kilomobi.bbfoot.Model;

import java.util.Date;

/**
 * Created by Nawako on 22/10/2015.
 */
public class Match {

    private int _id;
    private Date date;
    private int scoreRouge;
    private int scoreBleu;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getScoreRouge() {
        return scoreRouge;
    }

    public void setScoreRouge(int scoreRouge) {
        this.scoreRouge = scoreRouge;
    }

    public int getScoreBleu() {
        return scoreBleu;
    }

    public void setScoreBleu(int scoreBleu) {
        this.scoreBleu = scoreBleu;
    }
}
