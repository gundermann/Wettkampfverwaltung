package com.comphel.common.definition;

/**
 * Created by Admin on 16.07.13.
 */
abstract public class ShobuIpponRules {

    private long timeleft;

    private int wazariToWin;

    private int jogaiToLose;

    private int mubobiToLose;

    private int atenaiToLose;

    public long getTimeleft() {
        return timeleft;
    }

    public void setTimeleft(long timeleft) {
        this.timeleft = timeleft;
    }

    public int getJogaiToLose() {
        return jogaiToLose;
    }

    public void setJogaiToLose(int jogaiToLose) {
        this.jogaiToLose = jogaiToLose;
    }

    public int getWazariToWin() {
        return wazariToWin;
    }

    public void setWazariToWin(int wazariToWin) {
        this.wazariToWin = wazariToWin;
    }

    public int getMubobiToLose() {
        return mubobiToLose;
    }

    public void setMubobiToLose(int mubobiToLose) {
        this.mubobiToLose = mubobiToLose;
    }

    public int getAtenaiToLose() {
        return atenaiToLose;
    }

    public void setAtenaiToLose(int atenaiToLose) {
        this.atenaiToLose = atenaiToLose;
    }
}
