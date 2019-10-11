package exercise9.model.tiles;

import exercise9.model.SceneManager;
import exercise9.model.SoundManager;
import exercise9.controller.GameScreenController;
import javafx.scene.input.KeyEvent;
import exercise9.model.Player;

public class ExitTile extends ScaleTile
{
    public ExitTile(final int column, final int row, final String tileFileName) {
        super(column, row, tileFileName);
    }
    
    public void moveHere(final Player player, final KeyEvent keyEvent) {
        if (player.getGemsCollected() == GameScreenController.amountOfGems) {
            player.setFinalScore(player.getAmountOfSteps());
            SoundManager.WINNERMUSIC.play();
            SceneManager.show(SceneManager.WIN);
        }
        else {
            SoundManager.play(SoundManager.MEEPO);
        }
    }
}