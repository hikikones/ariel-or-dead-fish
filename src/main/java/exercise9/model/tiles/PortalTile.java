package exercise9.model.tiles;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;
import exercise9.model.Player;
import exercise9.model.Tile;

public class PortalTile extends Tile
{
    private Tile otherPortalTile;
    
    public PortalTile(final int column, final int row, final String tileFileName) {
        super(column, row, tileFileName);
    }
    
    @Override
    public void moveHere(final Player player, final KeyEvent keyEvent) {
        player.setRow(this.otherPortalTile.getRow());
        player.setColumn(this.otherPortalTile.getColumn());
        GridPane.setColumnIndex((Node)player, Integer.valueOf(this.otherPortalTile.getColumn()));
        GridPane.setRowIndex((Node)player, Integer.valueOf(this.otherPortalTile.getRow()));
        this.moveVisualGrid(keyEvent);
        final double distanceX = this.otherPortalTile.getLayoutX() - this.getLayoutX();
        final double distanceY = this.otherPortalTile.getLayoutY() - this.getLayoutY();
        this.visualGrid.setTranslateX(this.visualGrid.getTranslateX() - distanceX);
        this.visualGrid.setTranslateY(this.visualGrid.getTranslateY() - distanceY);
    }
    
    public Tile getOtherPortalTile() {
        return this.otherPortalTile;
    }
    
    public void setOtherPortalTile(final Tile otherPortal) {
        this.otherPortalTile = otherPortal;
    }
}