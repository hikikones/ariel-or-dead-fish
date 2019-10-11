package exercise9.model;

import javafx.scene.input.KeyEvent;

public abstract class TilePit extends Tile
{
    public TilePit(final int column, final int row, final String tileFileName) {
        super(column, row, tileFileName);
    }
    
    @Override
    public void moveHere(final Player player, final KeyEvent keyEvent) {
    }
}