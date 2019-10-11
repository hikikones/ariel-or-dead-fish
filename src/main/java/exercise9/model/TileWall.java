package exercise9.model;

import javafx.scene.input.KeyEvent;

public abstract class TileWall extends Tile
{
    public TileWall(final int column, final int row, final String tileFileName) {
        super(column, row, tileFileName);
    }
    
    @Override
    public void moveHere(final Player player, final KeyEvent keyEvent) {
    }
}