package polis.components.cursor;

import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.cursor.cursortile.CursorTileView;
import polis.components.buildings.BuildingTileManagerModel;

import java.util.ArrayList;

public abstract class CursorManager {

    private String tool;

    private final int gridSize;
    private final int cellSize;

    private final CursorTileView[][] tiles;
    private final BuildingTileManagerModel buildingField;

    public ArrayList<int[]> selected;

    public CursorManager(int gridSize, int cellSize, BuildingTileManagerModel buildingField, ArrayList<int[]> selected, CursorTileView[][] tiles){
        this.gridSize = gridSize;
        this.cellSize = cellSize;
        this.buildingField = buildingField;
        this.selected = selected;
        this.tiles = tiles;
    }

    public BuildingTileManagerModel getBuildingField() {
        return buildingField;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public CursorTileView[][] getTiles(){
        return tiles;
    }

    public CursorTileModel getTileModel(int row, int column){
        return tiles[row][column].getModel();
    }

    public String getTool() {
        return tool;
    }

    public ArrayList<int[]> getSelected() {
        return selected;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public int[] getTileFromCoordinates(double x, double y){
        int column = (int) ((x/cellSize)/2 + y/cellSize - 0.5);
        int row = (int) (-(x/cellSize)/2 + y/cellSize + 0.5);
        return new int[]{row,column};
    }

    public void hoover(double x, double y) {
        clearSelectedTiles();
        int[] coords = getTileFromCoordinates(x,y);
        addActiveTile(coords);
        colorSelectedTiles();
    }

    protected abstract void colorSelectedTiles();

    protected abstract void addActiveTile(int[] coords);

    public abstract void clearSelectedTiles();

    protected abstract void drag(double x, double y);

    protected abstract void setStartDrag(double x, double y);

    protected abstract void place();

}