package ch.quazz.caverna.score;

import java.util.ArrayList;

import ch.quazz.caverna.R;

public class TileItem extends Item {
    private Tile tile;
    private Token bonusToken;

    public TileItem(int title, int descriptionID, Tile tile, int imageID, Token bonusToken) {
        super(title, descriptionID, imageID);
        this.tile = tile;
        this.bonusToken = bonusToken;
    }

    public Tile getTile() {
        return tile;
    }

    public Token getBonusToken() {
        return bonusToken;
    }

    public void setTile(Tile tile) {
       this.tile = tile;
    }

    public void setBonusToken(Token bonusToken) {
        this.bonusToken = bonusToken;
    }
}
