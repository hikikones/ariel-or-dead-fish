package exercise9.model.tiles;

import javafx.scene.input.KeyEvent;
import exercise9.model.Player;
import exercise9.model.TileFloor;

public class FloorTile extends TileFloor
{
    public FloorTile(final int column, final int row, final String tileFileName) {
        super(column, row, tileFileName);
    }
    
    @Override
    public void moveHere(final Player player, final KeyEvent keyEvent) {
        super.moveHere(player, keyEvent);
    }
}