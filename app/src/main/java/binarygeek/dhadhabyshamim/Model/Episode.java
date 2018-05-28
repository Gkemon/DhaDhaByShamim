package binarygeek.dhadhabyshamim.Model;

import java.util.ArrayList;

/**
 * Created by uy on 5/28/2018.
 */

public class Episode {
    public String episodeName;
    public ArrayList<DhaDha> dhaDhas;

    public void setDhaDhas(ArrayList<DhaDha> dhaDhas) {
        this.dhaDhas = dhaDhas;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public ArrayList<DhaDha> getDhaDhas() {
        return dhaDhas;
    }

    public String getEpisodeName() {
        return episodeName;
    }
}
