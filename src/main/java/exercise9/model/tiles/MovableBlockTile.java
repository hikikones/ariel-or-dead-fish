package exercise9.model.tiles;

import exercise9.model.Tile;
import exercise9.model.TilePit;
import javafx.scene.input.KeyEvent;
import exercise9.model.Player;
import exercise9.model.TileMovableBlock;

public class MovableBlockTile extends TileMovableBlock
{
    public MovableBlockTile(final int column, final int row, final String tileFileName) {
        super(column, row, tileFileName);
    }
    
    @Override
    public void moveHere(final Player player, final KeyEvent keyEvent) {
        super.moveHere(player, keyEvent);
        if (this.nextTile instanceof TilePit) {
            this.moveTile(player, keyEvent);
            if (this.nextTile instanceof BrokenBridgeTile) {
                this.visualGrid.getChildren().remove((Object)this);
                this.swapTiles(this, this.nextTile);
                final Tile newFloor = new FloorTile(player.getColumn(), player.getRow(), "grassPlain.png");
                this.tileGrid[player.getRow()][player.getColumn()] = newFloor;
            }
            else if (this.nextTile instanceof PitTile) {
                Tile newFloor = new FloorTile(this.getColumn(), this.getRow(), "grassPlain.png");
                this.tileGrid[this.getRow()][this.getColumn()] = newFloor;
                newFloor = new FloorTile(this.nextTile.getColumn(), this.nextTile.getRow(), "grassPlain.png");
                this.tileGrid[this.nextTile.getRow()][this.nextTile.getColumn()] = newFloor;
                this.toBack();
            }
        }
    }
}