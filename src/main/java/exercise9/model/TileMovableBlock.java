package exercise9.model;

import exercise9.model.tiles.FakeWallTile;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import exercise9.model.tiles.CollectibleTile;
import javafx.scene.input.KeyEvent;

public abstract class TileMovableBlock extends Tile
{
    public Tile nextTile;
    private int rowDirection;
    private int columnDirection;
    
    public TileMovableBlock(final int column, final int row, final String tileFileName) {
        super(column, row, tileFileName);
    }
    
    @Override
    public void moveHere(final Player player, final KeyEvent keyEvent) {
        this.rowDirection = 0;
        this.columnDirection = 0;
        switch (keyEvent.getCode()) {
            case RIGHT: {
                this.columnDirection = 1;
                break;
            }
            case LEFT: {
                this.columnDirection = -1;
                break;
            }
            case DOWN: {
                this.rowDirection = 1;
                break;
            }
            case UP: {
                this.rowDirection = -1;
                break;
            }
        }
        this.nextTile = this.tileGrid[this.getRow() + this.rowDirection][this.getColumn() + this.columnDirection];
        if (this.nextTile instanceof TileFloor && !(this.nextTile instanceof CollectibleTile)) {
            this.moveTile(player, keyEvent);
        }
    }
    
    public void moveTile(final Player player, final KeyEvent keyEvent) {
        SoundManager.play(SoundManager.SLIDE);
        player.setRow(this.getRow());
        player.setColumn(this.getColumn());
        this.nextTile.setRow(this.getRow());
        this.nextTile.setColumn(this.getColumn());
        this.setRow(this.getRow() + this.rowDirection);
        this.setColumn(this.getColumn() + this.columnDirection);
        this.swapTiles(this, this.nextTile);
        GridPane.setColumnIndex((Node)this, Integer.valueOf(this.getColumn()));
        GridPane.setRowIndex((Node)this, Integer.valueOf(this.getRow()));
        GridPane.setColumnIndex((Node)player, Integer.valueOf(player.getColumn()));
        GridPane.setRowIndex((Node)player, Integer.valueOf(player.getRow()));
        this.moveVisualGrid(keyEvent);
        player.increaseAmountOfSteps();
        if (this.nextTile instanceof FakeWallTile) {
            System.out.println("Fake wall detected from the MovableBlock class!");
            this.nextTile.toFront();
        }
    }
    
    public void swapTiles(final Tile t1, final Tile t2) {
        final Tile temp = this.tileGrid[t1.getRow()][t1.getColumn()];
        this.tileGrid[t1.getRow()][t1.getColumn()] = this.tileGrid[t2.getRow()][t2.getColumn()];
        this.tileGrid[t2.getRow()][t2.getColumn()] = temp;
    }
}