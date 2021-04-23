package polis.components.playingfield.actors.actor.movers;

import polis.components.playingfield.actors.ActorField;
import polis.components.playingfield.actors.actor.Actor;
import polis.components.playingfield.actors.actor.stayers.Sleeper;
import polis.components.playingfield.buildings.tiles.BuildingTileModel;

public class Shopper extends Mover {

    public Shopper(int row, int column, ActorField actorField) {
        super(row, column, "commerce","#FA4F4C", "shopper", actorField);
    }

    @Override
    public Actor nextPhase() {
        return new Sleeper(getPosition()[0],getPosition()[1], getActorField());
    }

    @Override
    public boolean isDestinationReached(BuildingTileModel b) {
        return b.getName().equals(getDestination());
    }

}