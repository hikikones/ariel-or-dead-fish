package exercise9.model;

import javafx.scene.input.KeyEvent;
import exercise9.App;
import exercise9.controller.GameScreenController;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

public abstract class Tile extends ImageView
{
    public Tile[][] tileGrid;
    public GridPane visualGrid;
    public GridPane darkGrid;
    private String tilePath;
    private Image tileImage;
    private final double tileSize = 32.0;
    private int row;
    private int column;
    
    public Tile(final int column, final int row, final String tileFileName) {
        this.tileGrid = GameScreenController.tileGrid;
        this.visualGrid = GameScreenController.visualGrid;
        this.darkGrid = GameScreenController.darkGrid;
        //this.tilePath = "Exercise_09/model/resources/tileset/";
        this.tilePath = App.class.getResource("tileset/") + tileFileName;
        this.column = column;
        this.row = row;
        this.setImage(this.tileImage = new Image(this.tilePath));
        this.setFitWidth(32.0);
        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
    }
    
    public abstract void moveHere(final Player p0, final KeyEvent p1);
    
    public void moveVisualGrid(final KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case RIGHT: {
                this.visualGrid.setTranslateX(this.visualGrid.getTranslateX() - 32.0);
                break;
            }
            case LEFT: {
                this.visualGrid.setTranslateX(this.visualGrid.getTranslateX() + 32.0);
                break;
            }
            case DOWN: {
                this.visualGrid.setTranslateY(this.visualGrid.getTranslateY() - 32.0);
                break;
            }
            case UP: {
                this.visualGrid.setTranslateY(this.visualGrid.getTranslateY() + 32.0);
                break;
            }
        }
        if (this.darkGrid != null) {
            switch (keyEvent.getCode()) {
                case RIGHT: {
                    this.darkGrid.setTranslateX(this.darkGrid.getTranslateX() - 32.0);
                    break;
                }
                case LEFT: {
                    this.darkGrid.setTranslateX(this.darkGrid.getTranslateX() + 32.0);
                    break;
                }
                case DOWN: {
                    this.darkGrid.setTranslateY(this.darkGrid.getTranslateY() - 32.0);
                    break;
                }
                case UP: {
                    this.darkGrid.setTranslateY(this.darkGrid.getTranslateY() + 32.0);
                    break;
                }
            }
        }
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public void setRow(final int row) {
        this.row = row;
    }
    
    public void setColumn(final int column) {
        this.column = column;
    }
    
    public double getTileSize() {
        return 32.0;
    }
}