package ElevatorDesign;
import java.util.List;

class NearestElevatorStrategy implements ElevatorChoosingStrategy {
    @Override
    public ElevatorCar chooseElevator(List<ElevatorCar> elevators, ExternalRequest request) {
        ElevatorCar best = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorCar e : elevators) {
            if (e.getState() == State.MAINTENANCE || e.isEmergency()) continue;

            int distance = Math.abs(e.getCurrentFloor() - request.sourceFloor);
            if (distance < minDistance) {
                minDistance = distance;
                best = e;
            }
        }
        return best;
    }
}