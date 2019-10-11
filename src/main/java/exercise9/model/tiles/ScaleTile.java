package exercise9.model.tiles;

import exercise9.model.TileWall;

public class ScaleTile extends TileWall
{
    public ScaleTile(final int column, final int row, final String tileFileName) {
        super(column, row, tileFileName);
        final double scaleX = this.getImage().getWidth() / 16.0;
        final double scaleY = this.getImage().getHeight() / 16.0;
        this.setScaleX(scaleX);
        this.setScaleY(scaleY);
        this.setTranslateY(this.getTranslateY() - this.getTileSize());
    }
}