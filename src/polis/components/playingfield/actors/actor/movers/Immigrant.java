package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.stayers.Sleeper;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;

public class Immigrant extends Mover{

    public Immigrant(int row, int column, MoverManager moverManager) {
        super(row, column, moverManager, "residence","#d2d2d2");
    }

    @Override
    public Actor nextPhase() {
        return new Sleeper(getPosition()[0],getPosition()[1]);
    }

    @Override
    public boolean isDestinationReached(BuildingTileModel b) {
        return b.getFunction().equals(getDestination());
    }


}
