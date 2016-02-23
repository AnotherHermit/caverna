package ch.quazz.caverna.score;

import java.util.ArrayList;

import ch.quazz.caverna.R;

public class TokenItem extends Item{
    private Token token;
    private int minValueID;
    private int maxValueID;

    public TokenItem(int title, int descriptionID, Token token, int imageID, int minValueID, int maxValueID) {
        super(title, descriptionID, imageID);
        this.token = token;
        this.minValueID = minValueID;
        this.maxValueID = maxValueID;
    }

    public Token getToken() {
        return token;
    }

    public int getMinValueID() {
        return minValueID;
    }

    public int getMaxValueID() {
        return maxValueID;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void setMinValueID(int minValueID) {
        this.minValueID = minValueID;
    }

    public void setMaxValueID(int maxValueID) {
        this.maxValueID = maxValueID;
    }
}
