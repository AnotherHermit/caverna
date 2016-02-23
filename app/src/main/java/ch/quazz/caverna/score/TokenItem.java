package ch.quazz.caverna.score;

import java.util.ArrayList;

import ch.quazz.caverna.R;

public class TokenItem {
    private int titleID;
    private int descriptionID;
    private Token token;
    private int imageID;
    private int minValue;
    private int maxValue;

    public TokenItem(int title, int descriptionID, Token token, int imageID, int minValue, int maxValue) {
        this.titleID = title;
        this.descriptionID = descriptionID;
        this.token = token;
        this.imageID = imageID;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int getTitleID() {
        return titleID;
    }

    public int getDescriptionID() {
        return descriptionID;
    }

    public Token getToken() {
        return token;
    }

    public int getImageID() {
        return imageID;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setTitleID(int titleID) {
        this.titleID = titleID;
    }

    public void setDescriptionID(int descriptionID) {
        this.descriptionID = descriptionID;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public static ArrayList<TokenItem> getWealthItems() {
        ArrayList<TokenItem> list = new ArrayList<>();
        list.add(new TokenItem(R.string.dogs,           R.string.dogs_description, Token.Dogs,          R.drawable.dog,             R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.sheep,          R.string.dogs_description, Token.Sheep,         R.drawable.sheep,           R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.donkeys,        R.string.dogs_description, Token.Donkeys,       R.drawable.donkey,          R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.boars,     R.string.dogs_description, Token.Boars,         R.drawable.boar,            R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.cattle,         R.string.dogs_description, Token.Cattle,        R.drawable.cattle,          R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.grain,          R.string.dogs_description, Token.Grains,        R.drawable.grain,           R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.vegetable,      R.string.dogs_description, Token.Vegetables,    R.drawable.vegetable,       R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.rubies,         R.string.dogs_description, Token.Rubies,        R.drawable.ruby,            R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.gold,           R.string.dogs_description, Token.Gold,          R.drawable.gold,            R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.begging_markers,R.string.dogs_description, Token.BeggingMarkers,R.drawable.begging_marker,  R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.small_pastures, R.string.dogs_description, Token.SmallPastures, R.drawable.small_pasture,   R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.large_pastures, R.string.dogs_description, Token.LargePastures, R.drawable.large_pasture,   R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.ore_mines,      R.string.dogs_description, Token.OreMines,      R.drawable.ore_mine,        R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.ruby_mines,     R.string.dogs_description, Token.RubyMines,     R.drawable.ruby_mine,       R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.unused_tiles,   R.string.dogs_description, Token.UnusedSpace,   R.drawable.unused,          R.integer.wealth_min, R.integer.wealth_max));
        return list;
    }

    public static ArrayList<TokenItem> getFamilyItems() {
        ArrayList<TokenItem> list = new ArrayList<>();
        list.add(new TokenItem(R.string.dwarfs,     R.string.dogs_description, Token.Dwarfs,    R.drawable.dwarf,    R.integer.dwarfs_min,    R.integer.dwarfs_max));
        list.add(new TokenItem(R.string.dwellings,  R.string.dogs_description, Token.Dwellings, R.drawable.dwelling, R.integer.dwellings_min, R.integer.dwellings_max));
        return list;
    }

    public static ArrayList<TokenItem> getBonusItems() {
        ArrayList<TokenItem> list = new ArrayList<>();
        list.add(new TokenItem(R.string.ore,                R.string.dogs_description, Token.Ore,               R.drawable.ore,                R.integer.wealth_min,    R.integer.wealth_max));
        list.add(new TokenItem(R.string.stone,              R.string.dogs_description, Token.Stone,             R.drawable.stone,              R.integer.wealth_min, R.integer.wealth_max));
        list.add(new TokenItem(R.string.weapons,            R.string.dogs_description, Token.Weapons,           R.drawable.weapon,             R.integer.dwarfs_min,    R.integer.dwarfs_max));
        list.add(new TokenItem(R.string.adjacent_dwellings, R.string.dogs_description, Token.AdjacentDwellings, R.drawable.adjacent_dwellings, R.integer.adjacent_min, R.integer.adjacent_max));
        return list;
    }

}
