package exercise9.model;

import javafx.scene.input.KeyEvent;
import exercise9.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ImageView
{
    private Image lookDown;
    private Image lookLeft;
    private Image lookUp;
    private Image lookRight;
    private int column;
    private int row;
    private int amountOfSteps;
    private int finalScore;
    private int gemsCollected;
    
    public Player(final int row, final int column) {
        this.lookDown = new Image(App.class.getResource("tileset/") + "characterDown.png");
        this.lookLeft = new Image(App.class.getResource("tileset/") + "characterLeft.png");
        this.lookUp = new Image(App.class.getResource("tileset/") + "characterUp.png");
        this.lookRight = new Image(App.class.getResource("tileset/") + "characterRight.png");
        this.setRow(row);
        this.setColumn(column);
        this.amountOfSteps = 0;
        this.gemsCollected = 0;
        this.setImage(this.lookDown);
        this.setFitWidth(30.0);
        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
    }
    
    public void turn(final KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case RIGHT: {
                this.setImage(this.lookRight);
                break;
            }
            case LEFT: {
                this.setImage(this.lookLeft);
                break;
            }
            case DOWN: {
                this.setImage(this.lookDown);
                break;
            }
            case UP: {
                this.setImage(this.lookUp);
                break;
            }
        }
    }
    
    public void setRow(final int row) {
        this.row = row;
    }
    
    public void setColumn(final int column) {
        this.column = column;
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getFinalScore() {
        return this.finalScore;
    }
    
    public void setFinalScore(final int score) {
        this.finalScore = score;
    }
    
    public int getAmountOfSteps() {
        return this.amountOfSteps;
    }
    
    public void increaseAmountOfSteps() {
        ++this.amountOfSteps;
    }
    
    public int getGemsCollected() {
        return this.gemsCollected;
    }
    
    public void increaseGemsCollected() {
        ++this.gemsCollected;
    }
}