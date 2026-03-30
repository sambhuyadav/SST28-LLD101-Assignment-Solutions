package ElevatorDesign;
public class DirectionalQueueStrategy implements QueueProcessingStrategy {
    @Override
    public Direction getNextDirection(ElevatorCar e) {
        if (e.getState() == State.UP && !e.getUpRequests().isEmpty())
            return Direction.UP;
        if (e.getState() == State.DOWN && !e.getDownRequests().isEmpty())
            return Direction.DOWN;

        if (!e.getUpRequests().isEmpty())
            return Direction.UP;
        if (!e.getDownRequests().isEmpty())
            return Direction.DOWN;

        return null;
    }
}