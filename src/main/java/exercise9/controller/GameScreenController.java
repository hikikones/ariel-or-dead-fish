package exercise9.controller;

import javafx.scene.input.KeyEvent;
import java.util.Iterator;
import java.lang.reflect.InvocationTargetException;
import exercise9.model.tiles.FloorTile;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import exercise9.model.tiles.PortalTile;
import exercise9.model.tiles.FakeWallTile;
import exercise9.model.tiles.PitTile;
import exercise9.model.tiles.MovableDeadBodyTile;
import exercise9.model.tiles.MovableBlockTile;
import exercise9.model.tiles.CollectibleTile;
import exercise9.model.tiles.ExitTile;
import exercise9.model.tiles.ScaleTile;
import exercise9.model.tiles.BrokenBridgeTile;
import exercise9.model.tiles.WallTile;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import exercise9.App;
import exercise9.model.SoundManager;
import exercise9.model.SceneManager;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import exercise9.model.Player;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import exercise9.model.Tile;

public class GameScreenController
{
    private static String level;
    private static int rows;
    private static int columns;
    public static Tile[][] tileGrid;
    public static GridPane visualGrid;
    public static GridPane darkGrid;
    public static int amountOfGems;
    public static boolean darkMode;
    private ArrayList<Tile> scaledTiles;
    public static Player player;
    @FXML
    private VBox root;
    
    public GameScreenController() {
        this.scaledTiles = new ArrayList<Tile>();
    }
    
    @FXML
    public void initialize() {
        GameScreenController.level = "levels/" + WelcomeScreenController.chosenLevel;
        GameScreenController.rows = countRowsInLevel();
        GameScreenController.columns = countColumnsInLevel();
        GameScreenController.tileGrid = new Tile[GameScreenController.rows][GameScreenController.columns];
        (GameScreenController.visualGrid = new GridPane()).setAlignment(Pos.CENTER);
        this.root.setStyle("-fx-background-color: #292634");
        GameScreenController.amountOfGems = 0;
        if (GameScreenController.darkMode) {
            final StackPane stackPane = new StackPane();
            stackPane.getChildren().add(GameScreenController.visualGrid);
            (GameScreenController.darkGrid = new GridPane()).setAlignment(Pos.CENTER);
            for (int i = 0; i < GameScreenController.tileGrid.length; ++i) {
                for (int j = 0; j < GameScreenController.tileGrid[i].length; ++j) {
                    GameScreenController.darkGrid.add((Node)new Rectangle(32.0, 32.0, (Paint)Color.web("#292634")), j, i);
                }
            }
            stackPane.getChildren().add(GameScreenController.darkGrid);
            this.root.getChildren().add(stackPane);
        }
        else {
            this.root.getChildren().add(GameScreenController.visualGrid);
        }
        this.loadLevel();
        this.initializeMovement();
        GameScreenController.player.toFront();
        this.moveScaledTilesToFront();
        if (GameScreenController.darkMode) {
            this.revealTilesAroundPlayer();
        }
        SceneManager.fadeIn((Node)GameScreenController.visualGrid, Duration.seconds(1.0));
        SoundManager.GAMEMUSIC.setCycleCount(-1);
        SoundManager.GAMEMUSIC.play();
    }
    
    private void loadLevel() {
        int rowCount = 0;
        int columnCount = 0;
        PortalTile portal1 = null;
        try {
            //final InputStream levelStream = App.class.getResourceAsStream(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, GameScreenController.level));
            final InputStream levelStream = App.class.getResourceAsStream(GameScreenController.level);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(levelStream));
            int c;
            while ((c = reader.read()) != -1) {
                final char character = (char)c;
                switch (character) {
                    case '0': {
                        this.createTile((Class<? extends Tile>)WallTile.class, columnCount, rowCount, "cliffBottom.png");
                        break;
                    }
                    case '1': {
                        this.createTile((Class<? extends Tile>)WallTile.class, columnCount, rowCount, "cliffMiddle.png");
                        break;
                    }
                    case '2': {
                        this.createTile((Class<? extends Tile>)WallTile.class, columnCount, rowCount, "cliffTop.png");
                        break;
                    }
                    case ' ': {
                        this.createFloor(columnCount, rowCount, "grassPlain.png", true);
                        break;
                    }
                    case 'G': {
                        this.createFloor(columnCount, rowCount, "grassDetail.png", true);
                        break;
                    }
                    case 't': {
                        this.createFloor(columnCount, rowCount, "grassN.png", true);
                        break;
                    }
                    case 'y': {
                        this.createFloor(columnCount, rowCount, "grassNE.png", true);
                        break;
                    }
                    case 'h': {
                        this.createFloor(columnCount, rowCount, "grassE.png", true);
                        break;
                    }
                    case 'n': {
                        this.createFloor(columnCount, rowCount, "grassSE.png", true);
                        break;
                    }
                    case 'b': {
                        this.createFloor(columnCount, rowCount, "grassS.png", true);
                        break;
                    }
                    case 'v': {
                        this.createFloor(columnCount, rowCount, "grassSW.png", true);
                        break;
                    }
                    case 'f': {
                        this.createFloor(columnCount, rowCount, "grassW.png", true);
                        break;
                    }
                    case 'r': {
                        this.createFloor(columnCount, rowCount, "grassNW.png", true);
                        break;
                    }
                    case '=': {
                        this.createFloor(columnCount, rowCount, "dirtGrassHorizontal.png", true);
                        break;
                    }
                    case '^': {
                        this.createFloor(columnCount, rowCount, "dirtGrassVertical.png", true);
                        break;
                    }
                    case 'u': {
                        this.createFloor(columnCount, rowCount, "dirtGrassSW.png", true);
                        break;
                    }
                    case 'i': {
                        this.createFloor(columnCount, rowCount, "dirtGrassNW.png", true);
                        break;
                    }
                    case 'o': {
                        this.createFloor(columnCount, rowCount, "dirtGrassNE.png", true);
                        break;
                    }
                    case 'p': {
                        this.createFloor(columnCount, rowCount, "dirtGrassSE.png", true);
                        break;
                    }
                    case ':': {
                        this.createFloor(columnCount, rowCount, "dirtGrassW.png", true);
                        break;
                    }
                    case '-': {
                        this.createFloor(columnCount, rowCount, "dirtGrassN.png", true);
                        break;
                    }
                    case '.': {
                        this.createFloor(columnCount, rowCount, "dirtGrassE.png", true);
                        break;
                    }
                    case '_': {
                        this.createFloor(columnCount, rowCount, "dirtGrassS.png", true);
                        break;
                    }
                    case ';': {
                        this.createFloor(columnCount, rowCount, "dirtDetail.png", true);
                        break;
                    }
                    case ',': {
                        this.createFloor(columnCount, rowCount, "darkFloor.png", true);
                        break;
                    }
                    case 's': {
                        this.createFloor(columnCount, rowCount, "stairsMiddle.png", true);
                        break;
                    }
                    case 'a': {
                        this.createFloor(columnCount, rowCount, "stairsLeft.png", true);
                        break;
                    }
                    case 'd': {
                        this.createFloor(columnCount, rowCount, "stairsRight.png", true);
                        break;
                    }
                    case 'k': {
                        this.createFloor(columnCount, rowCount, "bridgeHorizontalMiddle.png", true);
                        break;
                    }
                    case 'j': {
                        this.createFloor(columnCount, rowCount, "bridgeHorizontalLeft.png", false);
                        this.createFloor(columnCount, rowCount, "dirtGrassHorizontal.png", true);
                        break;
                    }
                    case 'l': {
                        this.createFloor(columnCount, rowCount, "bridgeHorizontalRight.png", false);
                        this.createFloor(columnCount, rowCount, "grassPlain.png", true);
                        break;
                    }
                    case 'K': {
                        this.createFloor(columnCount, rowCount, "bridgeVerticalMiddle.png", true);
                        break;
                    }
                    case 'J': {
                        this.createFloor(columnCount, rowCount, "bridgeVerticalTop.png", true);
                        break;
                    }
                    case 'L': {
                        this.createFloor(columnCount, rowCount, "bridgeVerticalBottom.png", false);
                        this.createFloor(columnCount, rowCount, "darkFloor.png", true);
                        break;
                    }
                    case 'z': {
                        this.createTile((Class<? extends Tile>)BrokenBridgeTile.class, columnCount, rowCount, "bridgeHorizontalMiddleBroken.png");
                        break;
                    }
                    case '|': {
                        this.createTile((Class<? extends Tile>)WallTile.class, columnCount, rowCount, "houseWallVertical.png");
                        break;
                    }
                    case '/': {
                        this.createTile((Class<? extends Tile>)WallTile.class, columnCount, rowCount, "houseWallHorizontal.png");
                        break;
                    }
                    case '[': {
                        this.createFloor(columnCount, rowCount, "houseFloor.png", true);
                        break;
                    }
                    case '%': {
                        this.createTile((Class<? extends Tile>)WallTile.class, columnCount, rowCount, "houseBed.png");
                        break;
                    }
                    case '&': {
                        this.createTile((Class<? extends Tile>)WallTile.class, columnCount, rowCount, "houseDrawer.png");
                        break;
                    }
                    case 'I': {
                        this.createInvisibleWall(columnCount, rowCount);
                        break;
                    }
                    case 'T': {
                        this.createFloor(columnCount, rowCount, "grassPlain.png", false);
                        this.createTile((Class<? extends Tile>)ScaleTile.class, columnCount, rowCount, "treeBig.png");
                        break;
                    }
                    case 'R': {
                        this.createFloor(columnCount, rowCount, "grassPlain.png", false);
                        this.createTile((Class<? extends Tile>)ScaleTile.class, columnCount, rowCount, "treeBigPink.png");
                        break;
                    }
                    case 'Q': {
                        this.createTile((Class<? extends Tile>)WallTile.class, columnCount, rowCount, "waterRocksTop.png");
                        break;
                    }
                    case 'W': {
                        this.createTile((Class<? extends Tile>)WallTile.class, columnCount, rowCount, "waterWithShadow.png");
                        break;
                    }
                    case 'w': {
                        this.createTile((Class<? extends Tile>)WallTile.class, columnCount, rowCount, "waterPlain.png");
                        break;
                    }
                    case 'P': {
                        this.createFloor(columnCount, rowCount, "houseFloor.png", true);
                        this.createPlayer(columnCount, rowCount);
                        break;
                    }
                    case 'E': {
                        this.createTile((Class<? extends Tile>)ExitTile.class, columnCount, rowCount, "caveEntrance.png");
                        break;
                    }
                    case '*': {
                        this.createFloor(columnCount, rowCount, "grassPlain.png", false);
                        this.createTile((Class<? extends Tile>)CollectibleTile.class, columnCount, rowCount, "gem.png");
                        ++GameScreenController.amountOfGems;
                        break;
                    }
                    case 'B': {
                        this.createFloor(columnCount, rowCount, "grassPlain.png", false);
                        this.createTile((Class<? extends Tile>)MovableBlockTile.class, columnCount, rowCount, "rock.png");
                        break;
                    }
                    case 'Z': {
                        this.createFloor(columnCount, rowCount, "grassPlain.png", false);
                        this.createTile((Class<? extends Tile>)MovableDeadBodyTile.class, columnCount, rowCount, "deadBody.png");
                        break;
                    }
                    case 'X': {
                        this.createTile((Class<? extends Tile>)PitTile.class, columnCount, rowCount, "pit.png");
                        break;
                    }
                    case 'F': {
                        this.createFloor(columnCount, rowCount, "grassPlain.png", false);
                        this.createTile((Class<? extends Tile>)FakeWallTile.class, columnCount, rowCount, "grassToWall.png");
                        break;
                    }
                    case 'O': {
                        if (portal1 == null) {
                            portal1 = new PortalTile(columnCount, rowCount, "teleportStairs.png");
                            GameScreenController.visualGrid.add((Node)portal1, columnCount, rowCount);
                            GameScreenController.tileGrid[rowCount][columnCount] = (Tile)portal1;
                            break;
                        }
                        final PortalTile portal2 = new PortalTile(columnCount, rowCount, "teleportStairs2.png");
                        this.createFloor(columnCount, rowCount, "darkFloor.png", false);
                        GameScreenController.visualGrid.add((Node)portal2, columnCount, rowCount);
                        portal1.setOtherPortalTile(GameScreenController.tileGrid[rowCount][columnCount] = (Tile)portal2);
                        portal2.setOtherPortalTile((Tile)portal1);
                        break;
                    }
                    case '\n': {
                        ++rowCount;
                        columnCount = -1;
                        break;
                    }
                }
                ++columnCount;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    private static int countColumnsInLevel() {
        String firstLine = "";
        try {
            final InputStream levelStream = App.class.getResourceAsStream(GameScreenController.level);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(levelStream));
            firstLine = reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return firstLine.length();
    }
    
    private static int countRowsInLevel() {
        final String firstLine = "";
        int columnsInLevel = 0;
        try {
            final InputStream levelStream = App.class.getResourceAsStream(GameScreenController.level);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(levelStream));
            String line;
            while ((line = reader.readLine()) != null) {
                ++columnsInLevel;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return columnsInLevel;
    }
    
    private void initializeMovement() {
        SceneManager.getStage().getScene().setOnKeyPressed(keyEvent -> {
            int rowDirection = 0;
            int columnDirection = 0;
            switch (keyEvent.getCode()) {
                case RIGHT: {
                    columnDirection = 1;
                    break;
                }
                case LEFT: {
                    columnDirection = -1;
                    break;
                }
                case DOWN: {
                    rowDirection = 1;
                    break;
                }
                case UP: {
                    rowDirection = -1;
                    break;
                }
                case SPACE: {
                    SceneManager.show(SceneManager.GAME);
                    break;
                }
                case ESCAPE: {
                    SceneManager.fadeOut(SceneManager.START, Duration.seconds(1.0));
                    break;
                }
            }
            GameScreenController.player.turn(keyEvent);
            GameScreenController.tileGrid[GameScreenController.player.getRow() + rowDirection][GameScreenController.player.getColumn() + columnDirection].moveHere(GameScreenController.player, keyEvent);
            if (GameScreenController.darkMode) {
                this.revealTilesAroundPlayer();
            }
        });
    }
    
    private void createPlayer(final int columnCount, final int rowCount) {
        GameScreenController.player = new Player(rowCount, columnCount);
        GameScreenController.visualGrid.add((Node)GameScreenController.player, columnCount, rowCount);
    }
    
    private void createFloor(final int columnCount, final int rowCount, final String tileFileName, final boolean isKeyTile) {
        final Tile floor = (Tile)new FloorTile(columnCount, rowCount, tileFileName);
        GameScreenController.visualGrid.add((Node)floor, columnCount, rowCount);
        if (isKeyTile) {
            GameScreenController.tileGrid[rowCount][columnCount] = floor;
        }
        floor.toBack();
    }
    
    private void createInvisibleWall(final int columnCount, final int rowCount) {
        final Tile invisWall = (Tile)new WallTile(columnCount, rowCount, "grassPlain.png");
        GameScreenController.visualGrid.add((Node)invisWall, columnCount, rowCount);
        (GameScreenController.tileGrid[rowCount][columnCount] = invisWall).setOpacity(0.0);
        invisWall.toBack();
    }
    
    private void createTile(final Class<? extends Tile> tileType, final int column, final int row, final String tileFileName) {
        try {
            final Class[] arg = { Integer.TYPE, Integer.TYPE, String.class };
            final Tile tile = (Tile)tileType.getDeclaredConstructor((Class<?>[])arg).newInstance(column, row, tileFileName);
            GameScreenController.visualGrid.add((Node)tile, column, row);
            GameScreenController.tileGrid[row][column] = tile;
            if (tile instanceof ScaleTile) {
                this.scaledTiles.add(tile);
            }
            if (tile instanceof BrokenBridgeTile) {
                tile.toBack();
            }
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
        catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        }
    }
    
    private void moveScaledTilesToFront() {
        for (final Tile tile : this.scaledTiles) {
            tile.toFront();
        }
    }
    
    private void revealTilesAroundPlayer() {
        for (final Node node : GameScreenController.darkGrid.getChildren()) {
            if (GridPane.getColumnIndex(node) == GameScreenController.player.getColumn() && GridPane.getRowIndex(node) == GameScreenController.player.getRow()) {
                final int index = GameScreenController.darkGrid.getChildren().indexOf((Object)node);
                for (int i = -1; i <= 1; ++i) {
                    ((Node)GameScreenController.darkGrid.getChildren().get(index + i)).setOpacity(0.0);
                    ((Node)GameScreenController.darkGrid.getChildren().get(index + i + GameScreenController.darkGrid.getColumnCount())).setOpacity(0.0);
                    ((Node)GameScreenController.darkGrid.getChildren().get(index + i - GameScreenController.darkGrid.getColumnCount())).setOpacity(0.0);
                }
                break;
            }
        }
    }
    
    static {
        GameScreenController.darkGrid = null;
        GameScreenController.darkMode = false;
    }
}