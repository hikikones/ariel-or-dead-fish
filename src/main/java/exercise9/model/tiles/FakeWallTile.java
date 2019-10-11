package exercise9.model.tiles;

import javafx.scene.input.KeyEvent;
import exercise9.model.Player;
import exercise9.model.TileFloor;

public class FakeWallTile extends TileFloor
{
    public FakeWallTile(final int column, final int row, final String tileFileName) {
        super(column, row, tileFileName);
        this.setOpacity(0.7);
    }
    
    @Override
    public void moveHere(final Player player, final KeyEvent keyEvent) {
        this.toFront();
        super.moveHere(player, keyEvent);
    }
}