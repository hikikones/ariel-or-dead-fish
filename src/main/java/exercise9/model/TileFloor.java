package exercise9.model;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;

public abstract class TileFloor extends Tile
{
    public TileFloor(final int column, final int row, final String tileFileName) {
        super(column, row, tileFileName);
    }
    
    @Override
    public void moveHere(final Player player, final KeyEvent keyEvent) {
        player.setRow(this.getRow());
        player.setColumn(this.getColumn());
        GridPane.setColumnIndex((Node)player, Integer.valueOf(this.getColumn()));
        GridPane.setRowIndex((Node)player, Integer.valueOf(this.getRow()));
        this.moveVisualGrid(keyEvent);
        player.increaseAmountOfSteps();
    }
}