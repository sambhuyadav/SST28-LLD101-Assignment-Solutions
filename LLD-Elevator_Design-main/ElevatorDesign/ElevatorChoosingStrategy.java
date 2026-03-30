package ElevatorDesign;
import java.util.List;

interface ElevatorChoosingStrategy {
    ElevatorCar chooseElevator(List<ElevatorCar> elevators, ExternalRequest request);
}
