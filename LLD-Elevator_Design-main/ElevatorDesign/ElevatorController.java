package ElevatorDesign;
import java.util.List;

class ElevatorController {

    private ElevatorChoosingStrategy strategy;

    public ElevatorController(ElevatorChoosingStrategy strategy) {
        this.strategy = strategy;
    }

    public void handleExternalRequest(List<ElevatorCar> elevators, ExternalRequest request) {
        ElevatorCar chosen = strategy.chooseElevator(elevators, request);
        if (chosen != null) {
            chosen.addRequest(request.sourceFloor, request.direction);
        }
    }

    public void handleInternalRequest(ElevatorCar elevator, InternalRequest request) {
        Direction dir = (request.destinationFloor > elevator.getCurrentFloor())
                ? Direction.UP : Direction.DOWN;

        elevator.addRequest(request.destinationFloor, dir);
    }
}