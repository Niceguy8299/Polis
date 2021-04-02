package polis.components.cursor.cursors;

import polis.components.buildings.buildingtile.BuildingTileModel;
import polis.components.buildings.buildingtile.BuildingTileView;
import polis.components.cursor.CursorManager;
import polis.components.cursor.cursortile.CursorTileView;
import polis.components.buildings.BuildingTileManagerModel;

import java.util.ArrayList;
import java.util.Map;

public class CursorManagerSelect extends CursorManager {

    private static final Map<String,String> colors = Map.of(
            "bulldoze", "#D95B6699",
            "select", "#FFFFFF"
    );

    private static final int strokeWidth = 7;

    private final Map<String, Runnable> tools = Map.of(
            "select",this::select,
            "bulldoze",this::bulldoze
    );

    public CursorManagerSelect(int gridSize, int cellSize, BuildingTileManagerModel buildingField, ArrayList<int[]> selected, CursorTileView[][] tiles){
        super(gridSize, cellSize, buildingField, selected, tiles);
    }

    public void drag(double x, double y) { }

    public void setStartDrag(double x, double y) { }

    protected void place() {
        tools.get(getTool()).run();
    }

    public void clearSelectedTiles(){
        System.out.println("c");
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setStroke("#00000000",0);
        } selected.clear();
    }

    public void colorSelectedTiles(){
        String color = colors.get(getTool());
        for (int[] c : selected) {
            getTileModel(c[0],c[1]).setStroke(color,strokeWidth);
        }
    }

    public void hoover(double x, double y) {
        clearSelectedTiles();
        int[] coords = getTileFromCoordinates(x,y);
        addActiveTile(coords);
        colorSelectedTiles();
    }

    public void addActiveTile(int[] c){
        if (checkBounds(c)) {
            selected.add(c);
        }
    }

    public boolean checkBounds(int[] c){
        return ( c[0] >= 0 && c[0] < getGridSize() && c[1] >= 0 && c[1] < getGridSize());
    }

    public void bulldoze(){
        for (int[] c : selected) {
            BuildingTileView v = getBuildingField().getTiles()[c[0]][c[1]];
            if (v != null) {
                BuildingTileModel m = v.getModel();
                if(m.isDestructible()){
                    getBuildingField().deleteTile(c[0],c[1]);
                }
                for (int i = 0; i < getGridSize(); i += 1) {
                    for (int j = 0; j < getGridSize(); j += 1) {
                        if (getBuildingField().getTiles()[i][j] == v) {
                            getBuildingField().getTiles()[i][j] = null;
                            getTiles()[i][j].getModel().setStatus("AVAILABLE");
                        }
                    }
                }
            }
        }
    }

    public void select(){
        for (int[] c : selected) {
            BuildingTileView view = getBuildingField().getTiles()[c[0]][c[1]];
            if (view != null) {
                view.getModel().Update();
            }
        }
    }

}
