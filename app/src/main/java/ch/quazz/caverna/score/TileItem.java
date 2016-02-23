package ch.quazz.caverna.score;

import java.util.ArrayList;

import ch.quazz.caverna.R;

public class TileItem extends Item {
    private Tile tile;
    private boolean additionalCounter;

    public TileItem(int title, int descriptionID, Tile tile, int imageID, boolean additionalCounter) {
        super(title, descriptionID, imageID);
        this.tile = tile;
        this.additionalCounter = additionalCounter;
    }

    public Tile getTile() {
        return tile;
    }

    public boolean hasAdditionalCounter() {
        return additionalCounter;
    }

    public void setTile(Tile tile) {
       this.tile = tile;
    }

    public void setAdditionalCounter(boolean additionalCounter) {
        this.additionalCounter = additionalCounter;
    }

}
