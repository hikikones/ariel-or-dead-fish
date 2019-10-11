package exercise9.model.tiles;

import exercise9.model.Tile;
import exercise9.model.SoundManager;
import javafx.scene.input.KeyEvent;
import exercise9.model.Player;
import exercise9.model.TileFloor;

public class CollectibleTile extends TileFloor
{
    public CollectibleTile(final int column, final int row, final String tileFileName) {
        super(column, row, tileFileName);
    }
    
    @Override
    public void moveHere(final Player player, final KeyEvent keyEvent) {
        SoundManager.play(SoundManager.GEM);
        super.moveHere(player, keyEvent);
        this.visualGrid.getChildren().remove((Object)this);
        final Tile newFloor = (Tile)new FloorTile(this.getColumn(), this.getRow(), "grassPlain.png");
        (this.tileGrid[this.getRow()][this.getColumn()] = newFloor).toBack();
        player.increaseGemsCollected();
    }
}