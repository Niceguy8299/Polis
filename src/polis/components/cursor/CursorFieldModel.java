package polis.components.cursor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import polis.components.cursor.cursortile.CursorTileModel;
import polis.components.cursor.cursortile.CursorTileView;

import java.util.ArrayList;
import java.util.List;

public class CursorFieldModel implements Observable {

    private final List<InvalidationListener> listenerList = new ArrayList<>();

    private final int gridSize;
    private final int cellSize;

    private int pendingMode;
    private CursorTileView pendingView;


    public CursorFieldModel(int gridSize, int cellSize){
        this.gridSize = gridSize;
        this.cellSize = cellSize;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setTile(CursorTileModel tile){
        CursorTileView b = new CursorTileView(tile);
        pendingMode = 0;
        pendingView = b;
        fireInvalidationEvent();
    }

    public void deleteTiles(){
        pendingMode = 1;
        fireInvalidationEvent();
    }

    public int getPendingMode() {
        return pendingMode;
    }

    public CursorTileView getPendingView(){
        return  pendingView;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listenerList.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listenerList.remove(invalidationListener);
    }

    public void fireInvalidationEvent(){
        for(InvalidationListener listener : listenerList){
            listener.invalidated(this);
        }
    }

}
