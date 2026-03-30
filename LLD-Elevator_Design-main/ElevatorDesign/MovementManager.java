package ElevatorDesign;
class MovementManager {

    public void move(ElevatorCar e) {

        if (e.getDoor().isOpen()) {
            e.getDoor().close();
        }

        if (e.getSensorData().isOverWeight()) {
            e.getSafetyManager().handleOverweight(e);
            return;
        }

        if (!e.getSafetyManager().canMove(e.getSensorData(), e.isEmergency()))
            return;

        Direction direction = e.getQueueStrategy().getNextDirection(e);

        if (direction == null) {
            e.setState(State.IDLE);
            return;
        }

        if (direction == Direction.UP) {
            e.setState(State.UP);
            e.setCurrentFloor(e.getCurrentFloor() + 1);
        } else {
            e.setState(State.DOWN);
            e.setCurrentFloor(e.getCurrentFloor() - 1);
        }

        if (e.hasRequestAtCurrentFloor()) {
            System.out.println("Elevator " + e.getId() + 
                " reached floor " + e.getCurrentFloor());

            e.getDoor().open();
            e.removeCurrentFloorRequest();
        }
    }
}